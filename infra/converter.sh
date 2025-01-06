#!/bin/bash

# Check if the input file is provided
if [ -z "$1" ]; then
  echo "Usage: $0 <mysql_script.sql>"
  exit 1
fi

# Input and output files
mysql_script="$1"
postgres_script="postgres_$(basename "$mysql_script")"

# Create or overwrite the output file
> "$postgres_script"

# Convert the MySQL SQL file to PostgreSQL format
while IFS= read -r line; do
  # Remove MySQL-specific comments and settings
  if [[ "$line" =~ ^/\*! || "$line" =~ ^-- ]]; then
    continue  # Skip MySQL comments and settings
  fi

  # Remove LOCK TABLES and UNLOCK TABLES lines
  if [[ "$line" =~ ^LOCK\ TABLES || "$line" =~ ^UNLOCK\ TABLES ]]; then
    continue  # Skip LOCK TABLES and UNLOCK TABLES lines
  fi

  # Remove any blank lines
  if [[ -z "$line" ]]; then
    continue  # Skip empty lines
  fi

  # Convert backticks to double quotes for identifiers
  line=$(echo "$line" | sed 's/\`/\"/g')

  # Convert AUTO_INCREMENT to SERIAL for PostgreSQL
  line=$(echo "$line" | sed 's/AUTO_INCREMENT/SERIAL/g')

  # Replace MySQL boolean representation (1 and 0) with TRUE and FALSE
  line=$(echo "$line" | sed 's/\b1\b/TRUE/g')
  line=$(echo "$line" | sed 's/\b0\b/FALSE/g')

  # Convert MySQL NOW() to PostgreSQL CURRENT_TIMESTAMP
  line=$(echo "$line" | sed 's/NOW()/CURRENT_TIMESTAMP/g')

  # Remove MySQL-specific clauses (like ENGINE, CHARSET, etc.)
  line=$(echo "$line" | sed 's/ENGINE=[^ ]*//g')
  line=$(echo "$line" | sed 's/DEFAULT CHARSET=[^ ]*//g')

  # Add the transformed line to the PostgreSQL script file
  echo "$line" >> "$postgres_script"
done < "$mysql_script"

echo "PostgreSQL-compatible script generated: $postgres_script"
