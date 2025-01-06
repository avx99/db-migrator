


CREATE TABLE mysql.appointments (
  id int  NULL ,
  title varchar(50) null,
  subtitle varchar(191) null,
  ative_CTA varchar(191) null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL);



CREATE TABLE mysql.blocs (
  id int  NULL ,
  title varchar(191) null,
  subTitle varchar(191) null,
  ordre varchar(191) null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL,
  label_CTA varchar(191) NULL DEFAULT 'label',
  type varchar(191) NULL DEFAULT '',
  active_CTA smallint null,
  visible smallint null,
  tag varchar(191) null,
  lien_live varchar(191) DEFAULT NULL,
  description text,
  fond int  null,
  image varchar(191) null,
  subTitle2 text null,
  description2 text null,
  active smallint NULL DEFAULT '1',
  type_media varchar(191) NULL DEFAULT 'image',
  redirect_link varchar(191) null);





CREATE TABLE mysql.candidats (
  id int  NULL ,
  last_name varchar(191) null,
  first_name varchar(191) null,
  activity varchar(191) null,
  picture varchar(191) null,
  facebook_profile varchar(191) null,
  twitter_profile varchar(191) null,
  linkedin_profile varchar(191) null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL);



CREATE TABLE mysql.capsules (
  id int  NULL ,
  title text null,
  subTitle text null,
  description text null,
  lien_video varchar(191) null,
  "order" int null,
  date_ajout_capsule text null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL,
  banner text null,
  main_banner text null);





CREATE TABLE mysql.categories (
  id int  NULL ,
  name varchar(191) null,
  typeCategory varchar(191) null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL,
  active varchar(191) DEFAULT NULL);





CREATE TABLE mysql.cgu (
  id bigint  NULL ,
  titre varchar(191) null,
  description text null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL);



CREATE TABLE mysql.challenges (
  id int  NULL,
  title varchar(50) NULL,
  description text NULL,
  due_date text NULL,
  type_media varchar(10) DEFAULT NULL,
  media varchar(191) DEFAULT NULL,
  start_date text NULL,
  end_date text NULL,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL,
  category varchar(191) NULL,
  name_project varchar(191) NULL DEFAULT 'Nom de projet',
  name_project_label varchar(191) NULL DEFAULT 'Nom de projet',
  name_project_required varchar(191) NULL,
  name_project_active varchar(191) NULL,
  logo_project varchar(191) NULL DEFAULT 'Logo du projet',
  logo_project_label varchar(191) NULL DEFAULT 'Logo du projet',
  logo_project_required varchar(191) NULL,
  logo_project_active varchar(191) NULL,
  name_entity varchar(191) NULL DEFAULT 'Nom de l’entité',
  name_entity_label varchar(191) NULL DEFAULT 'Nom de l’entité',
  name_entity_required varchar(191) NULL,
  name_entity_active varchar(191) NULL,
  legal_status varchar(191) NULL DEFAULT 'Statut juridique',
  legal_status_label varchar(191) NULL DEFAULT 'Statut juridique',
  legal_status_required varchar(191) NULL,
  legal_status_active varchar(191) NULL,
  entity_logo varchar(191) NULL DEFAULT 'Logo entité',
  entity_logo_label varchar(191) NULL DEFAULT 'Logo entité',
  entity_logo_required varchar(191) NULL,
  entity_logo_active varchar(191) NULL,
  full_time varchar(191) NULL DEFAULT 'Plein temps OU partiel',
  full_time_label varchar(191) NULL DEFAULT 'Plein temps OU partiel',
  full_time_required varchar(191) NULL,
  full_time_active varchar(191) NULL,
  num_team varchar(191) NULL DEFAULT 'Nombre de co-équipiers',
  num_team_label varchar(191) NULL DEFAULT 'Nombre de co-équipiers',
  num_team_required varchar(191) NULL,
  num_team_active varchar(191) NULL,
  experience_years varchar(191) NULL DEFAULT 'Nombre d’années d’expérience',
  experience_years_label varchar(191) NULL DEFAULT 'Nombre d’années d’expérience',
  experience_years_required varchar(191) NULL,
  experience_years_active varchar(191) NULL,
  experience_price varchar(191) NULL DEFAULT 'Expériences et prix',
  experience_price_label varchar(191) NULL DEFAULT 'Expériences et prix',
  experience_price_required varchar(191) NULL,
  experience_price_active varchar(191) NULL,
  idea_project varchar(191) NULL DEFAULT 'Présentez l’idée du projet',
  idea_project_label varchar(191) NULL DEFAULT 'Présentez l’idée du projet',
  idea_project_required varchar(191) NULL,
  idea_project_active varchar(191) NULL,
  concept_project varchar(191) NULL DEFAULT 'Décrivez le concept du projet et ce que vous offrez comme produit/service.',
  concept_project_label varchar(191) NULL DEFAULT 'Décrivez le concept du projet et ce que vous offrez comme produit/service.',
  concept_project_required varchar(191) NULL,
  concept_project_active varchar(191) NULL,
  presentation_team varchar(191) NULL DEFAULT 'Présentez votre équipe',
  presentation_team_label varchar(191) NULL DEFAULT 'Présentez votre équipe',
  presentation_team_required varchar(191) NULL,
  presentation_team_active varchar(191) NULL,
  competitive_analysis varchar(191) NULL DEFAULT 'Qu’offrez-vous de mieux que vos concurrents?',
  competitive_analysis_label varchar(191) NULL DEFAULT 'Qu’offrez-vous de mieux que vos concurrents?',
  competitive_analysis_required varchar(191) NULL,
  competitive_analysis_active varchar(191) NULL,
  public_project varchar(191) NULL DEFAULT 'A qui s’adresse le projet?',
  public_project_label varchar(191) NULL DEFAULT 'A qui s’adresse le projet?',
  public_project_required varchar(191) NULL,
  public_project_active varchar(191) NULL,
  best_project varchar(191) NULL DEFAULT 'Finalement : Pensez-vous avoir le meilleur projet? Pourquoi?',
  best_project_label varchar(191) NULL DEFAULT 'Finalement : Pensez-vous avoir le meilleur projet? Pourquoi?',
  best_project_required varchar(191) NULL,
  best_project_active varchar(191) NULL,
  upload_file varchar(191) NULL DEFAULT 'Pièce jointe ( un/plusieurs documents )Business plan, prototypes ou prix remportés',
  upload_file_label varchar(191) NULL DEFAULT 'Pièce jointe ( un/plusieurs documents )Business plan, prototypes ou prix remportés',
  upload_file_required varchar(191) NULL,
  upload_file_active varchar(191) NULL,
  youtube_link varchar(191) NULL DEFAULT 'Vidéo de la participation',
  youtube_link_label varchar(191) NULL DEFAULT 'Vidéo de la participation',
  youtube_link_required varchar(191) NULL,
  youtube_link_active varchar(191) NULL,
  rs_portfolio_link varchar(191) NULL DEFAULT 'Lien vers RS ou portfolio en ligne (possibilité de rajouter jusqu’à 3)',
  rs_portfolio_link_label varchar(191) NULL DEFAULT 'Lien vers RS ou portfolio en ligne (possibilité de rajouter jusqu’à 3)',
  rs_portfolio_link_required varchar(191) NULL,
  rs_portfolio_link_active varchar(191) NULL,
  motivation varchar(191) NULL DEFAULT 'description/motivation',
  motivation_label varchar(191) NULL DEFAULT 'description/motivation',
  motivation_required varchar(191) NULL,
  motivation_active varchar(191) NULL,
  label_presentation_1 varchar(191) DEFAULT 'Parlez-nous de votre projet',
  label_presentation_2 varchar(191) DEFAULT 'Présentez-nous votre dream team',
  label_presentation_3 varchar(191) DEFAULT 'Ce qui rend votre projet unique ?',
  label_presentation_4 varchar(191) DEFAULT 'A qui s’adresse votre projet ?',
  label_presentation_5 varchar(191) DEFAULT 'Pensez-vous détenir le meilleur projet ?',
  active varchar(191) DEFAULT NULL,
  team_count int  NULL,
  media_demo text NULL,
  type_media_demo varchar(20) NULL,
  categories_required text NULL,
  categories_active text NULL,
  join_team SMALLINT NULL,
  question_1 text NULL,
  question_1_label text NULL,
  question_1_required text NULL,
  question_1_active text NULL,
  question_2 text NULL,
  question_2_label text NULL,
  question_2_required text NULL,
  question_2_active text NULL,
  question_3 text NULL,
  question_3_label text NULL,
  question_3_required text NULL,
  question_3_active text NULL
);













CREATE TABLE mysql.challenges2 (
  id int  NULL ,
  title text null,
  description text null,
  due_date text null,
  media varchar(191) DEFAULT NULL,
  start_date text null,
  end_date text null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL,
  lastname varchar(50) NULL DEFAULT 'Nom',
  lastname_label varchar(50) NULL DEFAULT 'Nom',
  firstname varchar(50) NULL DEFAULT 'Prénom',
  firstname_label varchar(50) NULL DEFAULT 'Prénom',
  birthday varchar(50) NULL DEFAULT 'Date de naissance',
  birthday_label varchar(50) NULL DEFAULT 'Date de naissance',
  function varchar(50) NULL DEFAULT 'Fonction',
  function_label varchar(50) NULL DEFAULT 'Fonction',
  organisme varchar(50) NULL DEFAULT 'Organisme',
  organisme_label varchar(50) NULL DEFAULT 'Organisme',
  city varchar(50) NULL DEFAULT 'Ville de résidence',
  city_label varchar(50) NULL DEFAULT 'Ville de résidence',
  country varchar(50) NULL DEFAULT 'Pays de résidence',
  country_label varchar(50) NULL DEFAULT 'Pays de résidence',
  startup_name varchar(50) NULL DEFAULT 'Nom de la Startup',
  startup_name_label varchar(50) NULL DEFAULT 'Nom de la Startup',
  office_address varchar(50) NULL DEFAULT 'Adresse du siège social',
  office_address_label varchar(50) NULL DEFAULT 'Adresse du siège social',
  maturity_stage varchar(50) NULL DEFAULT 'Stade de maturité',
  maturity_stage_label varchar(50) NULL DEFAULT 'Stade de maturité',
  nbr_founders varchar(50) NULL DEFAULT 'Nombre de fondateurs',
  nbr_contributors varchar(50) NULL DEFAULT 'Quel est le nombre de vos collaborateurs internes?',
  website varchar(50) NULL DEFAULT 'Site internet',
  website_label varchar(50) NULL DEFAULT 'Site internet',
  cv varchar(50) NULL DEFAULT 'CV équipes',
  cv_label varchar(50) NULL DEFAULT 'CV équipes',
  startup_description varchar(50) NULL DEFAULT 'Insérez une petite description de votre Startup',
  startup_description_label varchar(50) NULL DEFAULT 'Insérez une petite description de votre Startup',
  startup_video varchar(191) null,
  startup_video_label varchar(50) NULL DEFAULT 'Insérez une vidéo d’une minute de votre Startup',
  problem varchar(191) NULL DEFAULT 'Décrivez le problème dont souffrent vos utilisateurs et que vous essayez de résoudre',
  problem_label varchar(191) NULL DEFAULT 'Décrivez le problème dont souffrent vos utilisateurs et que vous essayez de résoudre',
  solution varchar(191) NULL DEFAULT 'Décrivez votre solution, et expliquez en quoi elle est innovante',
  solution_label varchar(191) NULL DEFAULT 'Décrivez votre solution, et expliquez en quoi elle est innovante',
  solution_how varchar(191) NULL DEFAULT 'Comment fonctionne votre solution?',
  solution_how_label varchar(191) NULL DEFAULT 'Comment fonctionne votre solution?',
  market varchar(191) NULL DEFAULT 'Quel est votre marché cible',
  market_label varchar(191) NULL DEFAULT 'Quel est votre marché cible',
  market_size varchar(191) NULL DEFAULT 'Quelle est la taille de votre marché?',
  market_size_label varchar(191) NULL DEFAULT 'Quelle est la taille de votre marché?',
  market_user varchar(191) NULL DEFAULT 'Quel est votre utilisateur cible',
  market_user_label varchar(191) NULL DEFAULT 'Quel est votre utilisateur cible',
  client1 varchar(191) NULL DEFAULT 'Comment allez-vous parler avec votre client?',
  client1_label varchar(191) NULL DEFAULT 'Comment allez-vous parler avec votre client?',
  client2 varchar(191) NULL DEFAULT 'Comment allez-vous distribuer votre solution',
  client2_label varchar(191) NULL DEFAULT 'Comment allez-vous distribuer votre solution',
  pricing_model varchar(191) NULL DEFAULT 'Quel est votre modèle de pricing',
  pricing_model_label varchar(191) NULL DEFAULT 'Quel est votre modèle de pricing',
  challenges varchar(191) NULL DEFAULT 'Quels sont les principaux challenges auxquels votre stratup fait face aujourd’hui?',
  challenges_label varchar(191) NULL DEFAULT 'Quels sont les principaux challenges auxquels votre stratup fait face aujourd’hui?',
  goal varchar(191) NULL DEFAULT 'Que souhaitez-vous réaliser à travers ce programme?',
  goal_model_label varchar(191) NULL DEFAULT 'Que souhaitez-vous réaliser à travers ce programme?',
  more_docs varchar(191) NULL DEFAULT 'Souhaitez-vous partager d’autres documents complémentaires?',
  more_docs_label varchar(191) NULL DEFAULT 'Souhaitez-vous partager d’autres documents complémentaires?',
  question_1 text null,
  question_1_label text null,
  question_2 text null,
  question_2_label text null,
  question_3 text null,
  question_3_label text null,
  categories_required text null,
  categories_active smallint NULL DEFAULT '1',
  facebook varchar(191) null,
  facebook_label varchar(191) null,
  facebook_active smallint NULL DEFAULT '1',
  facebook_required smallint NULL DEFAULT '0',
  linkedin varchar(191) null,
  linkedin_label varchar(191) null,
  linkedin_active smallint NULL DEFAULT '1',
  linkedin_required smallint NULL DEFAULT '0',
  instagram varchar(191) null,
  instagram_label varchar(191) null,
  instagram_active smallint NULL DEFAULT '1',
  instagram_required smallint NULL DEFAULT '0',
  twitter varchar(191) null,
  twitter_label varchar(191) null,
  twitter_active smallint NULL DEFAULT '1',
  twitter_required smallint NULL DEFAULT '0',
  lastname_required smallint NULL DEFAULT '0',
  firstname_required smallint NULL DEFAULT '0',
  birthday_required smallint NULL DEFAULT '0',
  function_required smallint NULL DEFAULT '0',
  organisme_required smallint NULL DEFAULT '0',
  city_required smallint NULL DEFAULT '0',
  country_required smallint NULL DEFAULT '0',
  startup_name_required smallint NULL DEFAULT '0',
  office_address_required smallint NULL DEFAULT '0',
  maturity_stage_required smallint NULL DEFAULT '0',
  nbr_founders_active smallint NULL DEFAULT '1',
  nbr_founders_required smallint NULL DEFAULT '0',
  nbr_contributors_active smallint NULL DEFAULT '1',
  nbr_contributors_required smallint NULL DEFAULT '0',
  website_active smallint NULL DEFAULT '1',
  website_required smallint NULL DEFAULT '0',
  cv_active smallint NULL DEFAULT '1',
  cv_required smallint NULL DEFAULT '0',
  startup_description_required smallint NULL DEFAULT '0',
  startup_video_active smallint NULL DEFAULT '1',
  startup_video_required smallint NULL DEFAULT '0',
  problem_active smallint NULL DEFAULT '1',
  problem_required smallint NULL DEFAULT '0',
  solution_active smallint NULL DEFAULT '1',
  solution_required smallint NULL DEFAULT '0',
  solution_how_active smallint NULL DEFAULT '1',
  solution_how_required smallint NULL DEFAULT '0',
  market_active smallint NULL DEFAULT '1',
  market_required smallint NULL DEFAULT '0',
  market_size_active smallint NULL DEFAULT '1',
  market_size_required smallint NULL DEFAULT '0',
  market_user_active smallint NULL DEFAULT '1',
  market_user_required smallint NULL DEFAULT '0',
  client1_active smallint NULL DEFAULT '1',
  client1_required smallint NULL DEFAULT '0',
  client2_active smallint NULL DEFAULT '0',
  client2_required smallint NULL DEFAULT '0',
  pricing_model_active smallint NULL DEFAULT '1',
  pricing_model_required smallint NULL DEFAULT '0',
  challenges_active smallint NULL DEFAULT '1',
  challenges_required smallint NULL DEFAULT '0',
  goal_model_active smallint NULL DEFAULT '0',
  goal_model_required smallint NULL DEFAULT '0',
  more_docs_active smallint NULL DEFAULT '1',
  more_docs_required smallint NULL DEFAULT '0',
  active smallint NULL DEFAULT '0',
  startup_logo_required smallint null,
  goal_active smallint NULL DEFAULT '1',
  avatar_required smallint NULL DEFAULT '1',
  label_user_identity varchar(191) NULL DEFAULT 'Identité de l’utilisateur',
  label_general_info varchar(191) NULL DEFAULT 'Informations générales',
  label_elevator_pitch varchar(191) NULL DEFAULT 'Elevator Pitch',
  label_problem_solution varchar(191) NULL DEFAULT 'Problème & Solution',
  label_user_acquisition_business_model varchar(191) NULL DEFAULT 'Utilisateurs, Acquisition, & Business Model',
  label_needs_expection varchar(191) NULL DEFAULT 'Besoins & Attentes',
  message_soumettre_titre varchar(191) null,
  message_soumettre_contenu text null,
  lastname_active smallint NULL DEFAULT '1',
  firstname_active smallint NULL DEFAULT '1',
  birthday_active smallint NULL DEFAULT '1',
  function_active smallint NULL DEFAULT '1',
  organisme_active smallint NULL DEFAULT '1',
  city_active smallint NULL DEFAULT '1',
  country_active smallint NULL DEFAULT '1',
  startup_name_active smallint NULL DEFAULT '1',
  startup_logo_active smallint NULL DEFAULT '1',
  office_address_active smallint NULL DEFAULT '1',
  maturity_stage_active smallint NULL DEFAULT '1',
  avatar_active smallint NULL DEFAULT '1',
  startup_description_active smallint NULL DEFAULT '1',
  message_save_titre varchar(191) null,
  message_save_contenu text null,
  message_not_now varchar(191) null,
  startup_logo_mention text,
  cv_mention text,
  startup_video_mention text,
  more_docs_mention text,
  avatar_mention text);





CREATE TABLE mysql.cibles (
  id int  NULL ,
  title varchar(50) null,
  description text null,
  banner varchar(191) null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL);





CREATE TABLE mysql.cities (
  id int  NULL ,
  name varchar(50) null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL);





CREATE TABLE mysql.contacts (
  id int  NULL ,
  firstname varchar(191) null,
  lastname varchar(191) null,
  email varchar(191) null,
  object varchar(191) null,
  typeContact int null,
  message text null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL);





CREATE TABLE mysql.contributors (
  id int  NULL ,
  last_name varchar(191) null,
  first_name varchar(191) null,
  activity varchar(191) null,
  picture varchar(191) null,
  facebook_profile varchar(191) null,
  twitter_profile varchar(191) null,
  linkedin_profile varchar(191) null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL,
  type varchar(191) NULL DEFAULT 'Evenement',
  message text null);





CREATE TABLE mysql.email_contacts (
  id int  NULL ,
  email varchar(191) null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL);





CREATE TABLE mysql.emails_newsletter (
  id int  NULL ,
  email varchar(191) null,
  unsubscribe smallint null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL,
  token varchar(191) DEFAULT NULL,
  confirmed smallint NULL DEFAULT '0');





CREATE TABLE mysql.emissions (
  id int  NULL ,
  title text null,
  subTitle text null,
  description text null,
  subTitle2 text null,
  description2 text null,
  lien_live varchar(191) null,
  image varchar(191) null,
  type_media varchar(191) null,
  saison varchar(191) null,
  active_presentation smallint NULL DEFAULT '1',
  title_prime text null,
  subTitle_prime text null,
  active_cta_prime smallint NULL DEFAULT '1',
  cta_prime text null,
  active_prime smallint NULL DEFAULT '1',
  title_startup text null,
  active_cta_startup smallint NULL DEFAULT '1',
  cta_startup text null,
  active_startup smallint NULL DEFAULT '1',
  title_capsule text null,
  subTitle_capsule text null,
  active_cta_capsule smallint NULL DEFAULT '1',
  cta_capsule text null,
  active_capsule smallint NULL DEFAULT '1',
  title_investisseur text null,
  active_investisseur smallint NULL DEFAULT '1',
  title_entrepreneurs text null,
  active_entrepreneurs smallint NULL DEFAULT '1',
  title_galeries text null,
  active_galerie smallint NULL DEFAULT '1',
  title_fond text null,
  subTitle_fond text null,
  active_fond smallint NULL DEFAULT '1',
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL);





CREATE TABLE mysql.entrepreneurs (
  id int  NULL ,
  last_name varchar(191) null,
  first_name varchar(191) null,
  activity varchar(191) null,
  picture varchar(191) null,
  description text null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL,
  url_portrait text,
  linkedIn text,
  instagram text,
  facebook text,
  twitter text,
  url_video text,
  label_cta text,
  active_portrait smallint null,
  active_fb smallint null,
  active_insta smallint null,
  active_twitter smallint null,
  active_linkd smallint null);





CREATE TABLE mysql.events (
  id int  NULL ,
  title text null,
  description text null,
  banner varchar(191) null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL,
  view_count int  NULL DEFAULT '0',
  active smallint null,
  inwi_event smallint NULL DEFAULT '0',
  cta_inscription_externe varchar(191) DEFAULT NULL,
  url_inscription_externe varchar(191) DEFAULT NULL,
  without_city smallint NULL DEFAULT '0',
  start_date_inscription text null,
  end_date_inscription text null,
  is_live smallint NULL DEFAULT '0',
  places_live int NULL DEFAULT '0',
  compan_on smallint NULL DEFAULT '0',
  live_label varchar(191) DEFAULT NULL);





CREATE TABLE mysql.failed_jobs (
  id bigint  NULL ,
  connection text null,
  queue text null,
  payload text null,
  exception text null,
  failed_at text NULL DEFAULT CURRENT_TIMESTAMP);



CREATE TABLE mysql.formations (
  id int  NULL ,
  title text null,
  subTitle text null,
  description text null,
  lien_video varchar(191) null,
  "order" int null,
  date_ajout_formation text null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL,
  video_thumbnail text null);





CREATE TABLE mysql.galerie (
  id int  NULL ,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL,
  type varchar(191) null,
  media varchar(191) null,
  best_off smallint null);



CREATE TABLE mysql.investisseurs (
  id int  NULL ,
  last_name varchar(191) null,
  first_name varchar(191) null,
  activity varchar(191) null,
  picture varchar(191) null,
  description text null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL,
  url_portrait text,
  linkedIn text,
  instagram text,
  facebook text,
  twitter text,
  url_video text,
  label_cta text,
  active_portrait smallint null,
  active_fb smallint null,
  active_insta smallint null,
  active_twitter smallint null,
  active_linkd smallint null);





CREATE TABLE mysql.jobs (
  id bigint  NULL ,
  queue varchar(191) null,
  payload text null,
  attempts smallint  null,
  reserved_at int  DEFAULT NULL,
  available_at int  null,
  created_at int  null);





CREATE TABLE mysql.locales (
  id int  NULL ,
  code varchar(191) null,
  name varchar(191) DEFAULT NULL,
  is_default smallint NULL DEFAULT '0',
  is_enabled smallint NULL DEFAULT '0',
  sort_order int NULL DEFAULT '0');








CREATE TABLE mysql.migrations (
  id int  NULL ,
  migration varchar(191) null,
  batch int null);





CREATE TABLE mysql.motivations (
  id int  NULL ,
  title varchar(50) null,
  banner varchar(191) null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL,
  description varchar(191) NULL DEFAULT '');





CREATE TABLE mysql.notifications (
  id char(36) null,
  type varchar(191) null,
  notifiable_id int  null,
  notifiable_type varchar(191) null,
  data text null,
  read_at text NULL DEFAULT NULL,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL);





CREATE TABLE mysql.partenaires (
  id int  NULL ,
  nom varchar(191) null,
  banner varchar(191) null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL);



CREATE TABLE mysql.partners (
  id int  NULL ,
  name varchar(50) null,
  logo varchar(191) null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL,
  link text null);





CREATE TABLE mysql.password_reset_tokens (
  email varchar(191) null,
  token varchar(191) null,
  created_at text NULL DEFAULT NULL);



CREATE TABLE mysql.password_resets (
  email varchar(191) null,
  token varchar(191) null,
  created_at text NULL DEFAULT NULL
) ;






CREATE TABLE mysql.personal_access_tokens (
  id bigint  NULL ,
  tokenable_type varchar(191) null,
  tokenable_id bigint  null,
  name varchar(191) null,
  token varchar(64) null,
  abilities text,
  last_used_at text NULL DEFAULT NULL,
  expires_at text NULL DEFAULT NULL,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL);





CREATE TABLE mysql.primes (
  id int  NULL ,
  title text null,
  subTitle text null,
  description text null,
  lien_video varchar(191) null,
  "order" int null,
  date_ajout_prime text null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL,
  fond int  null,
  title_fond1 text null,
  title_fond2 text null,
  banner text null,
  main_banner text null);





CREATE TABLE mysql.profils (
  id int  NULL ,
  name varchar(60) null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL);





CREATE TABLE mysql.programme_motivation (
  id int  NULL ,
  title varchar(50) null,
  banner varchar(191) null,
  description varchar(191) null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL);





CREATE TABLE mysql.programmes (
  id int  NULL ,
  title varchar(191) null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL,
  banner varchar(191) null,
  description text null,
  start_date text null,
  challenge_tag smallint null,
  event_tag smallint null,
  ordre int null,
  redirect_type varchar(191) null,
  challenge_id int DEFAULT NULL,
  challenge2_id int DEFAULT NULL,
  event_id int DEFAULT NULL,
  is_emission smallint NULL DEFAULT '0',
  redirect_link varchar(191) null);





CREATE TABLE mysql.publications (
  id int  NULL ,
  title varchar(50) null,
  banner varchar(191) null,
  presentation text null,
  contenu varchar(191) null,
  date_publication text null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL,
  active smallint null,
  type_publication varchar(191) null);





CREATE TABLE mysql.question2_options (
  id int  NULL ,
  sondage_id bigint  null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL,
  titre varchar(191) null,
  icon varchar(191) null,
  description text null);





CREATE TABLE mysql.questions (
  id int  NULL ,
  question text null,
  reponse text null,
  ordre int null,
  active smallint null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL);





CREATE TABLE mysql.releases (
  id int  NULL ,
  title varchar(191) DEFAULT NULL,
  description text,
  type varchar(191) null,
  image varchar(191) null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL,
  active smallint null,
  date_publication text null,
  active_home smallint NULL DEFAULT '0');





CREATE TABLE mysql.reponse_sondages (
  id int  NULL ,
  email_user varchar(191) null,
  reponse varchar(191) null,
  autre_reponse varchar(191) null,
  sondage_id int null,
  autres_precisions varchar(191) DEFAULT NULL,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL);





CREATE TABLE mysql.settings_page (
  id int  NULL ,
  name varchar(191) null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL);





CREATE TABLE mysql.sliders (
  id int  NULL ,
  banner varchar(191) null,
  description text,
  tag varchar(191) DEFAULT NULL,
  start_date text null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL,
  active_description smallint null,
  active_tag smallint null,
  active_date smallint null,
  active_ville smallint null,
  active smallint null,
  active_bouton smallint null,
  label varchar(191) DEFAULT NULL,
  lien_redirect varchar(191) DEFAULT NULL,
  title varchar(191) DEFAULT NULL,
  subTitre varchar(191) DEFAULT NULL,
  active_CTA smallint null,
  label_cta varchar(191) DEFAULT NULL,
  active_title smallint null,
  active_subTitle varchar(191) null,
  banner_mobile varchar(191) null,
  "order" int null);





CREATE TABLE mysql.sondages (
  id bigint  NULL ,
  titre varchar(191) null,
  image varchar(191) null,
  active smallint NULL DEFAULT '0',
  activeReponses smallint NULL DEFAULT '0',
  type varchar(30) null,
  question_2 varchar(191) null,
  question_3 varchar(191) DEFAULT NULL,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL);





CREATE TABLE mysql.startups (
  id int  NULL ,
  nom varchar(191) null,
  banner varchar(191) null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL,
  link varchar(191) null,
  active smallint null,
  title varchar(191) null,
  sub_title varchar(191) null,
  description text null,
  active_home smallint null,
  "order" int null,
  active_emission smallint null,
  is_participant smallint NULL DEFAULT '0');





CREATE TABLE mysql.tagformations (
  id int  NULL ,
  thematique varchar(191) null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL);





CREATE TABLE mysql.tags (
  id int  NULL ,
  thematique varchar(191) null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL,
  ordre int null);





CREATE TABLE mysql.translate_models (
  id int  NULL ,
  locale varchar(191) null,
  model_id varchar(191) DEFAULT NULL,
  model_type varchar(191) DEFAULT NULL,
  attribute_data text);









CREATE TABLE mysql.type_contact (
  id int  NULL ,
  title varchar(191) null,
  body text null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL);





CREATE TABLE mysql.uploads (
  id int  NULL ,
  file text null,
  file_name text null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL);





CREATE TABLE mysql.users (
  id int  NULL ,
  name varchar(191) null,
  email varchar(191) DEFAULT NULL,
  password varchar(191) null,
  remember_token varchar(100) DEFAULT NULL,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL,
  birthday date null,
  avatar varchar(191) DEFAULT NULL,
  gender varchar(191) null,
  city varchar(191) null,
  tel varchar(191) null,
  inwi_tel varchar(191) null,
  fonction varchar(191) null,
  entreprise varchar(191) null,
  profil varchar(191) null,
  competencies varchar(191) null,
  nickname varchar(191) DEFAULT NULL,
  social_user_id varchar(191) DEFAULT NULL,
  verified smallint NULL DEFAULT '0',
  email_token varchar(191) DEFAULT NULL,
  token varchar(254) DEFAULT NULL,
  expire_date text null,
  statut varchar(191) null,
  newsletter smallint null,
  firstname varchar(191) null,
  lastname varchar(191) null,
  experience_years varchar(191) null,
  studies_level varchar(191) null,
  file varchar(191) null,
  type_profile varchar(191) null,
  number_of_connections int null,
  flag_avatar smallint NULL DEFAULT '0');





CREATE TABLE mysql.users_bo (
  id int  NULL ,
  username varchar(191) null,
  email varchar(191) null,
  password varchar(191) null,
  role varchar(191) null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL,
  token text,
  last_connection text DEFAULT NULL,
  exper_first_connection varchar(191) DEFAULT NULL);





CREATE TABLE mysql.challenge_category (
  id int  NULL ,
  category_id int  null,
  challenge_id int  null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL);








CREATE TABLE mysql.challenge_category_v2 (
  id int  NULL ,
  category_id int  null,
  challenge_id int  null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL);








CREATE TABLE mysql.challenge_media (
  id int  NULL ,
  type varchar(191) null,
  media varchar(191) null,
  challenge_id int  null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL,
  best_off smallint null);







CREATE TABLE mysql.challenge_media_v2 (
  id int  NULL ,
  type varchar(191) null,
  media varchar(191) null,
  challenge_id int  null,
  best_off smallint null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL);







CREATE TABLE mysql.challenge_moderator (
  id int  NULL ,
  moderator_id int  null,
  challenge_id int  null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL);








CREATE TABLE mysql.challenge_moderator_v2 (
  id int  NULL ,
  moderator_id int  null,
  challenge_id int  null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL);








CREATE TABLE mysql.communiques (
  id int  NULL ,
  title varchar(191) null,
  description varchar(191) null,
  content text null,
  media varchar(191) DEFAULT NULL,
  favorite smallint NULL DEFAULT '0',
  statut varchar(191) null,
  viewCount int NULL DEFAULT '0',
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL,
  category varchar(191) null,
  author varchar(191) null,
  banner varchar(191) null,
  name_project varchar(191) DEFAULT NULL,
  logo_project varchar(191) DEFAULT NULL,
  name_entity varchar(191) DEFAULT NULL,
  legal_status varchar(191) DEFAULT NULL,
  entity_logo varchar(191) DEFAULT NULL,
  full_time varchar(191) DEFAULT NULL,
  num_team int DEFAULT NULL,
  experience_years int DEFAULT NULL,
  experience_price varchar(191) DEFAULT NULL,
  idea_project text,
  concept_project text,
  presentation_team text,
  competitive_analysis text,
  public_project text,
  best_project text,
  upload_file varchar(191) DEFAULT NULL,
  youtube_link varchar(191) DEFAULT NULL,
  rs_portfolio_link varchar(191) DEFAULT NULL,
  motivation text,
  user_id int  null,
  coup_coeur varchar(191) DEFAULT NULL,
  challenge_id int  null,
  rs_link1_type varchar(191) DEFAULT NULL,
  rs_link1_value varchar(191) DEFAULT NULL,
  rs_link2_type varchar(191) DEFAULT NULL,
  rs_link2_value varchar(191) DEFAULT NULL,
  rs_link3_type varchar(191) DEFAULT NULL,
  rs_link3_value varchar(191) DEFAULT NULL,
  question_1 text null,
  question_2 text null,
  question_3 text null);








CREATE TABLE mysql.communiques_categories (
  id int  NULL ,
  category_id int  null,
  communique_id int  null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL);








CREATE TABLE mysql.emission_capsule (
  id int  NULL ,
  emission_id int  null,
  capsule_id int  null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL);








CREATE TABLE mysql.emission_entrepreneur (
  id int  NULL ,
  emission_id int  null,
  entrepreneur_id int  null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL);








CREATE TABLE mysql.emission_galeries (
  id int  NULL ,
  type varchar(191) null,
  media varchar(191) null,
  emission_id int  null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL);







CREATE TABLE mysql.emission_investisseur (
  id int  NULL ,
  emission_id int  null,
  investisseur_id int  null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL);








CREATE TABLE mysql.emission_prime (
  id int  NULL ,
  emission_id int  null,
  prime_id int  null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL);








CREATE TABLE mysql.emission_startup (
  id int  NULL ,
  emission_id int  null,
  startup_id int  null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL);








CREATE TABLE mysql.event_category (
  id int  NULL ,
  event_id int  null,
  category_id int  null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL);








CREATE TABLE mysql.event_city (
  id int  NULL ,
  city_id int  null,
  event_id int  null,
  address varchar(191) null,
  longitude varchar(191) DEFAULT NULL,
  latitude varchar(191) DEFAULT NULL,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL,
  start_date text null,
  end_date text null,
  places int  null,
  live varchar(191) null
);







CREATE TABLE mysql.event_city_contributors (
  id int  NULL ,
  event_city_id int  null,
  contributor_id int  null,
  "order" int  null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL);








CREATE TABLE mysql.event_city_participant (
  id int  NULL ,
  event_city_id int  null,
  user_id int  null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL,
  email_sent smallint NULL DEFAULT '0'

);







CREATE TABLE mysql.event_city_partners (
  id int  NULL ,
  event_city_id int  null,
  partner_id int  null,
  "order" int  null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL);








CREATE TABLE mysql.event_contributor (
  id int  NULL ,
  event_id int  null,
  contributor_id int  null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL,
  "order" int  null);








CREATE TABLE mysql.event_media (
  id int  NULL ,
  type varchar(191) null,
  media varchar(191) null,
  best_off smallint null,
  event_id int  null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL);







CREATE TABLE mysql.event_moderator (
  id int  NULL ,
  moderator_id int  null,
  event_id int  null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL);








CREATE TABLE mysql.event_participant (
  id int  NULL ,
  event_id int  null,
  user_id int  null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL,
  email_sent smallint NULL DEFAULT '0'

);







CREATE TABLE mysql.event_partner (
  id int  NULL ,
  event_id int  null,
  partner_id int  null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL,
  "order" int  null);








CREATE TABLE mysql.event_planing (
  id int  NULL ,
  event_city_id int  null,
  day varchar(191) null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL);







CREATE TABLE mysql.event_planing2 (
  id int  NULL ,
  event_id int  null,
  day varchar(191) null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL);







CREATE TABLE mysql.formation_contributors (
  id int  NULL ,
  contributor_id int  null,
  formation_id int  null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL);






CREATE TABLE mysql.formation_tags (
  id int  NULL ,
  tag_id int  null,
  formation_id int  null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL);








CREATE TABLE mysql.newsletter (
  id int  NULL ,
  email varchar(191) null,
  user_id int  DEFAULT NULL,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL,
  unsubscribe smallint null,
  cause text null);







CREATE TABLE mysql.notification (
  id int  NULL ,
  object varchar(191) null,
  object_id int null,
  content varchar(191) null,
  seen smallint null,
  user_id int  DEFAULT NULL,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL);







CREATE TABLE mysql.planing_timeslot (
  id int  NULL ,
  "from" varchar(191) null,
  "to" varchar(191) null,
  description text,
  event_planing_id int  null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL);







CREATE TABLE mysql.planing_timeslot2 (
  id int  NULL ,
  "from" varchar(191) null,
  "to" varchar(191) null,
  description varchar(191) DEFAULT NULL,
  event_planing_id int  null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL);





CREATE TABLE mysql.prime_contributors (
  id int  NULL ,
  contributor_id int  null,
  prime_id int  null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL);






CREATE TABLE mysql.prime_startups (
  id int  NULL ,
  startup_id int  null,
  prime_id int  null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL);








CREATE TABLE mysql.programme_category (
  id int  NULL ,
  category_id int  null,
  programme_id int  null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL);








CREATE TABLE mysql.programme_city (
  id int  NULL ,
  city_id int  null,
  programe_id int  null,
  address varchar(191) null,
  longitude varchar(191) DEFAULT NULL,
  latitude varchar(191) DEFAULT NULL,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL
);







CREATE TABLE mysql.projects (
  id int  NULL ,
  name_project varchar(191) DEFAULT NULL,
  logo_project varchar(191) DEFAULT NULL,
  logo_project_name varchar(191) DEFAULT NULL,
  name_entity varchar(191) DEFAULT NULL,
  legal_status varchar(191) DEFAULT NULL,
  entity_logo varchar(191) DEFAULT NULL,
  entity_logo_name varchar(191) DEFAULT NULL,
  full_time varchar(191) DEFAULT NULL,
  num_team int DEFAULT '1',
  experience_years int DEFAULT NULL,
  experience_price varchar(191) DEFAULT NULL,
  idea_project text,
  concept_project text,
  presentation_team text,
  competitive_analysis text,
  public_project text,
  best_project text,
  youtube_link varchar(191) DEFAULT NULL,
  rs_portfolio_link varchar(191) DEFAULT NULL,
  motivation text,
  status text,
  challenge_id int  null,
  user_id int  null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL,
  rs_link1_type varchar(191) DEFAULT NULL,
  rs_link1_value varchar(191) DEFAULT NULL,
  rs_link2_type varchar(191) DEFAULT NULL,
  rs_link2_value varchar(191) DEFAULT NULL,
  rs_link3_type varchar(191) DEFAULT NULL,
  rs_link3_value varchar(191) DEFAULT NULL,
  is_article smallint null,
  viewCount int  NULL DEFAULT '0',
  evaluation_1 int NULL DEFAULT '0',
  evaluation_2 int NULL DEFAULT '0',
  evaluation_3 int NULL DEFAULT '0',
  evaluation_4 int NULL DEFAULT '0',
  evaluation_5 int NULL DEFAULT '0',
  evaluation_6 int NULL DEFAULT '0',
  evaluation_7 int NULL DEFAULT '0',
  evaluation_8 int NULL DEFAULT '0',
  status_1 varchar(191) null,
  status_2 varchar(191) null,
  comment_1 text null,
  comment_2 text null,
  creation_status smallint NULL DEFAULT '0',
  question_1 text null,
  question_2 text null,
  question_3 text null);








CREATE TABLE mysql.projects2 (
  id int  NULL ,
  participant_avatar varchar(191) null,
  lastname varchar(191) DEFAULT NULL,
  firstname varchar(191) DEFAULT NULL,
  birthday date DEFAULT NULL,
  function varchar(191) DEFAULT NULL,
  organisme varchar(191) DEFAULT NULL,
  city varchar(191) DEFAULT NULL,
  country varchar(191) DEFAULT NULL,
  startup_name varchar(191) DEFAULT NULL,
  startup_logo varchar(191) DEFAULT NULL,
  startup_logo_name varchar(191) DEFAULT NULL,
  office_address varchar(191) DEFAULT NULL,
  maturity_stage varchar(191) DEFAULT NULL,
  nbr_founders int DEFAULT NULL,
  nbr_contributors int DEFAULT NULL,
  website varchar(191) DEFAULT NULL,
  facebook varchar(191) DEFAULT NULL,
  twitter varchar(191) DEFAULT NULL,
  instagram varchar(191) DEFAULT NULL,
  linkedin varchar(191) DEFAULT NULL,
  startup_description text,
  startup_video varchar(191) DEFAULT NULL,
  problem text,
  solution text,
  solution_how text,
  market varchar(191) DEFAULT NULL,
  market_size varchar(191) DEFAULT NULL,
  market_user varchar(191) DEFAULT NULL,
  client1 text,
  client2 text,
  pricing_model varchar(191) DEFAULT NULL,
  challenges text,
  goal text,
  more_docs varchar(191) DEFAULT NULL,
  more_docs_name varchar(191) DEFAULT NULL,
  question1 varchar(191) DEFAULT NULL,
  question2 varchar(191) DEFAULT NULL,
  question3 varchar(191) DEFAULT NULL,
  is_article smallint null,
  viewCount int  NULL DEFAULT '0',
  num_team int NULL DEFAULT '1',
  evaluation_5 int NULL DEFAULT '0',
  evaluation_6 int NULL DEFAULT '0',
  evaluation_7 int NULL DEFAULT '0',
  evaluation_8 int NULL DEFAULT '0',
  status_2 varchar(191) null,
  comment_2 text null,
  status text,
  creation_status smallint NULL DEFAULT '0',
  active smallint NULL DEFAULT '0',
  challenge_id int  null,
  user_id int  null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL,
  question1_2 text,
  question2_2 text,
  question3_2 text,
  active_front smallint NULL DEFAULT '0',
  status_valid text,
  date_publication text null,
  is_saved smallint null,
  flag_logo smallint NULL DEFAULT '0');








CREATE TABLE mysql.projects2_experts (
  id int  NULL ,
  user_id int  null,
  project_id int  null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL,
  has_rake smallint null);








CREATE TABLE mysql.projects_categories (
  id int  NULL ,
  category_id int  null,
  project_id int  null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL);






CREATE TABLE mysql.projects_categories_v2 (
  id int  NULL ,
  category_id int  null,
  project_id int  null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL);






CREATE TABLE mysql.publication_city (
  id int  NULL ,
  city_id int  null,
  publication_id int  null,
  address varchar(191) null,
  longitude varchar(191) DEFAULT NULL,
  latitude varchar(191) DEFAULT NULL,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL

);







CREATE TABLE mysql.question_tag (
  id int  NULL ,
  tag_id int  null,
  question_id int  null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL);








CREATE TABLE mysql.release_content (
  id int  NULL ,
  type_content varchar(191) null,
  content text null,
  image varchar(191) null,
  title varchar(191) null,
  release_id int  null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL,
  "order" int null,
  video_thumbnail text null);







CREATE TABLE mysql.releases_categories (
  id int  NULL ,
  category_id int  null,
  release_id int  null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL);








CREATE TABLE mysql.request_profile (
  id int  NULL ,
  project_id int  null,
  profile_id int  null,
  institution varchar(191) DEFAULT NULL,
  experience int  DEFAULT NULL,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL,
  status varchar(191) null);








CREATE TABLE mysql.request_profile_v2 (
  id int  NULL ,
  project_id int  null,
  profile_id int  null,
  institution varchar(191) DEFAULT NULL,
  experience int  DEFAULT NULL,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL);






CREATE TABLE mysql.section_challenge (
  id int  NULL ,
  name varchar(191) null,
  challenge_id int  null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL);







CREATE TABLE mysql.sections_settings (
  id int  NULL ,
  key varchar(60) null,
  description text null,
  image varchar(191) null,
  color varchar(191) NULL DEFAULT '#ffffff',
  settings_page_id int  null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL
);







CREATE TABLE mysql.settings_bloc (
  id int  NULL ,
  name varchar(191) null,
  settings_page_id int  null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL);







CREATE TABLE mysql.slider_city (
  id int  NULL ,
  city_id int  null,
  slider_id int  null,
  address varchar(191) null,
  longitude varchar(191) DEFAULT NULL,
  latitude varchar(191) DEFAULT NULL,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL
);







CREATE TABLE mysql.social_accounts (
  provider_user_id varchar(191) null,
  provider varchar(191) null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL,
  user_id int  null
);





CREATE TABLE mysql.temoignage (
  id int  NULL ,
  message text null,
  status smallint NULL DEFAULT '0',
  user_id int  DEFAULT NULL,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL,
  "order" int  NULL DEFAULT '1');







CREATE TABLE mysql.user_attachements (
  id int  NULL ,
  file text null,
  file_name text null,
  user_id int  null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL);







CREATE TABLE mysql.attachements (
  id int  NULL ,
  file varchar(191) null,
  project_id int  DEFAULT NULL,
  communique_id int  DEFAULT NULL,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL,
  file_name varchar(191) DEFAULT NULL);








CREATE TABLE mysql.attachements_v2 (
  id int  NULL ,
  file varchar(191) null,
  project_id int  DEFAULT NULL,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL,
  file_name varchar(191) DEFAULT NULL,
  type varchar(191) null);







CREATE TABLE mysql.project_members (
  id int  NULL ,
  project_id int  null,
  user_id int  DEFAULT NULL,
  profile_id int  DEFAULT NULL,
  email varchar(191) null,
  status varchar(191) null,
  isAdmin smallint null,
  can_update_project smallint null,
  can_manage_team smallint  null,
  can_manage_requests smallint  null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL);









CREATE TABLE mysql.project_members_v2 (
  id int  NULL ,
  project_id int  null,
  user_id int  DEFAULT NULL,
  profile_id int  DEFAULT NULL,
  email varchar(191) null,
  status varchar(191) null,
  isAdmin smallint null,
  can_update_project smallint null,
  can_manage_team smallint  null,
  can_manage_requests smallint  null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL);







CREATE TABLE mysql.project_notes (
  id int  NULL ,
  observation text,
  project_id int  null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL,
  section_note text,
  user_id int  null,
  note_globale DOUBLE PRECISION DEFAULT NULL,
  by_admin varchar(191) DEFAULT NULL);








CREATE TABLE mysql.project_requests (
  id int  NULL ,
  user_id int  null,
  project_id int  null,
  profile_id int  null,
  message text null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL,
  status varchar(191) null,
  request_profile_id int  null);










CREATE TABLE mysql.project_requests_v2 (
  id int  NULL ,
  user_id int  null,
  project_id int  null,
  profile_id int  null,
  message text null,
  request_profile_id int  null,
  status varchar(191) null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL);








CREATE TABLE mysql.project_responses (
  id int  NULL ,
  response varchar(191) DEFAULT NULL,
  question_id int null,
  file varchar(191) DEFAULT NULL,
  file_name varchar(191) DEFAULT NULL,
  section_name varchar(191) DEFAULT NULL,
  type_question varchar(191) DEFAULT NULL,
  project_id int  null,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL,
  question text null,
  question_active smallint NULL DEFAULT '0',
  response_option text);







CREATE TABLE mysql.questions_challenge (
  id int  NULL ,
  question_label varchar(191) null,
  question varchar(191) null,
  question_required smallint NULL DEFAULT '0',
  section_name varchar(191) null,
  type_question varchar(191) DEFAULT NULL,
  challenge_id int  DEFAULT NULL,
  section_id int  DEFAULT NULL,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL,
  question_active smallint NULL DEFAULT '0',
  active_autre smallint null,
  file_mention text,
  image_mention text);








CREATE TABLE mysql.settings (
  id int  NULL ,
  key varchar(60) null,
  value text null,
  description varchar(191) DEFAULT NULL,
  created_at text NULL DEFAULT NULL,
  updated_at text NULL DEFAULT NULL,
  settings_bloc_id int  null,
  color varchar(15) NULL DEFAULT '#ffffff',
  visibility smallint NULL DEFAULT '1',
  type varchar(191) NULL DEFAULT 'label',
  url varchar(191) DEFAULT NULL
);


