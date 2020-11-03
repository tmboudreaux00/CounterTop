USE countertop_db;


# TRUNCATE drops all data from the table but leaves the table intact.
# FOREIGN_KEY_CHECKS will prevent truncating tables with foreign keys.
# FOREIGN_KEY_CHECKS set to 0 will disable the check. Enable the check at the end of truncating by setting to 1.
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE users;
TRUNCATE tags;
TRUNCATE recipes;
TRUNCATE comments;
SET FOREIGN_KEY_CHECKS = 1;

#Resets AUTO_INCREMENT to value
ALTER TABLE users AUTO_INCREMENT = 1;
ALTER TABLE tags AUTO_INCREMENT = 1;
ALTER TABLE recipes AUTO_INCREMENT = 1;
ALTER TABLE comments AUTO_INCREMENT = 1;

#Seeds users
INSERT INTO users (dob, email, password, signup_date, url, username)
VALUES
(CURDATE(), 'admin@admin.com', 'admin', CURDATE(), 'www.countertop.com', 'admin'),
('1991/01/01', 'austin@austin.com', 'austin', CURDATE(), 'www.countertop.com', 'austin'),
('1992/02/02', 'charles@charles.com', 'charles', CURDATE(), 'www.countertop.com', 'charles'),
('1993/03/03', 'david@david.com', 'david', CURDATE(), 'www.countertop.com', 'david'),
('1994/04/04', 'tim@tim.com', 'tim', CURDATE(), 'www.countertop.com', 'tim')
;


#Seeds Recipes
INSERT INTO recipes (date_published, description, name, instructions, skill_level, url, user_id)
VALUES (CURDATE(), 'Recipe #1 Description', 'Recipe #1', 'yum', 'beginner', 'www.countertop.com', '5'),
       (CURDATE(), 'Recipe #2 Description', 'Recipe #2', 'yum', 'easy', 'www.countertop.com', '4'),
       (CURDATE(), 'Recipe #3 Description', 'Recipe #3', 'yum', 'medium', 'www.countertop.com', '3'),
       (CURDATE(), 'Recipe #4 Description', 'Recipe #4', 'yum', 'difficult', 'www.countertop.com', '2'),
       (CURDATE(), 'Recipe #5 Description', 'Recipe #5', 'yum', 'chef', 'www.countertop.com', '3'),
       (CURDATE(), 'Recipe #6 Description', 'Recipe #6', 'yum', 'beginner', 'www.countertop.com', '4'),
       (CURDATE(), 'Recipe #7 Description', 'Recipe #7', 'yum', 'easy', 'www.countertop.com', '5'),
       (CURDATE(), 'Recipe #8 Description', 'Recipe #8', 'yum', 'medium', 'www.countertop.com', '4'),
       (CURDATE(), 'Recipe #9 Description', 'Recipe #9', 'yum', 'difficult', 'www.countertop.com', '3'),
       (CURDATE(), 'Recipe #10 Description', 'Recipe #10', 'yum', 'chef', 'www.countertop.com', '2'),
       (CURDATE(), 'Cacio e Pepe is a pasta dish from modern Roman cuisine. "Cacio e pepe" means "cheese and pepper" in several central Italian dialects.',
        'Cacio e Pepe', 'yum', 'beginner', 'https://bit.ly/31KeydW', 3),
       (CURDATE(), 'This Cilantro Lime Rice is super easy to make and better than Chipotle\'s and super easy to make! It goes great with Asian or Mexican food', 'yum','Cilantro Lime Rice', 'easy', 'https://bit.ly/37MQieW', 3 );

#Seeds Tags
INSERT INTO tags (name)
VALUES
('italian'),
('mexican'),
('chinese'),
('american'),
('indian'),
('vietnamese'),
('brazilian'),
('french');


#Seeds Tags
INSERT INTO tags (name)
VALUES
('italian'),
('mexican'),
('chinese'),
('american'),
('indian'),
('vietnamese'),
('brazilian'),
('french');

#Seeds Comments
INSERT INTO comments (comment_body, date, liked, recipe_id, user_id)
VALUES
('This is delicious', CURDATE(), true, 1, 1),
('This is delicious', CURDATE(), false, 2, 3),
('This is delicious', CURDATE(), false, 3, 4),
('This is delicious', CURDATE(), true, 4, 5),
('This is delicious', CURDATE(), false, 1, 1),
('This is delicious', CURDATE(), true, 1, 1),
('This is delicious', CURDATE(), true, 1, 1);

INSERT INTO comments (comment_body, date, liked, parent_comment_id, recipe_id, user_id)
VALUES
('This is delicious', CURDATE(), false, 1, 5, 1),
('This is delicious', CURDATE(), false, 2, 1, 1),
('This is delicious', CURDATE(), false, 6, 1, 1),
('This is delicious', CURDATE(), false, 1, 1, 1),
('This is delicious', CURDATE(), true, 2, 1, 1),
('This is delicious', CURDATE(), true, 4, 1, 1),
('This is delicious', CURDATE(), true, 8, 1, 1),
('This is delicious', CURDATE(), true, 2, 1, 1),
('This is delicious', CURDATE(), false, 1, 1, 1),
('This is delicious', CURDATE(), true, 3, 1, 1);


ALTER TABLE recipes AUTO_INCREMENT = 11;

INSERT INTO recipes (date_published, description, name, instructions, skill_level, url, user_id)
VALUES
(CURDATE(), 'This Cilantro Lime Rice is super easy to make and better than Chipotle\'s and super easy to make! It goes great with Asian or Mexican food', 'Cilantro Lime Rice', 'yum', 'easy', 'https://bit.ly/37MQieW', 3 );

INSERT INTO recipes (date_published, description, name, instructions, skill_level, url, user_id)
VALUES
(CURDATE(), 'This grilled mushroom and swiss cheese burger is the best burger you\'ll ever make and it\'s fairly simple.', 'Grilled Mushroom & Swiss Burger', 'yum','easy', 'https://bit.ly/35I9Jmo', 3 );

INSERT INTO ingredients (name)
VALUES ('pecorino romano'),
       ('pasta'),
       ('black pepper'),
       ('salt');
