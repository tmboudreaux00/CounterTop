USE countertop_db;

INSERT INTO users (dob, email, password, signup_date, url, username)
VALUES ('1995/09/29', 'charles@codeup.com', 'password', curdate(),'https://bit.ly/3mr0fml', 'charlesLikesCooking');

INSERT INTO recipes (description, name, skill_level, url, user_id, date_published)
VALUES ('Cacio e pepe is a pasta dish from modern Roman cuisine. "Cacio e pepe" means "cheese and pepper" in several central Italian dialects.',
        'Cacio e Pepe',
        'intermediate',
        'https://bit.ly/35HTCpc',
        1,
        curdate()),
       ('Cacio e pepe is a pasta dish from modern Roman cuisine. "Cacio e pepe" means "cheese and pepper" in several central Italian dialects.',
        'Cacio e Pepe',
        'intermediate',
        'https://bit.ly/35HTCpc',
        1,
        curdate())
       ;