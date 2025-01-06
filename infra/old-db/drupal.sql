select * from user_attachements;
select * from users;
select * from users_bo;

select * from releases;
select * from release_content;
select * from releases_categories;
select * from categories;

-- les blocs
select * from blocs;

-- detail actualite  : 
select
	*
from
	releases r
left join release_content rc on
	r.id = rc.release_id
LEFT join releases_categories rct on
	rct.release_id = r.id
LEFT join categories c on
	rct.category_id = c.id;

-- liste actualites:

select * from releases r;
select * from projects2 p2;
	

-- cgu: 
select * from cgu c;



-- faq:
select * from questions;
select * from question2_options qo ;
SELECT * from questions_challenge qc; 







