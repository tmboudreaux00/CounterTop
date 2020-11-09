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




INSERT INTO users_favorites(user_id, recipe_id)
VALUES (3, 2),
       (3, 3),
       (3, 4),
       (3, 5),
       (3, 6),
       (3, 7),
       (3, 8),
       (3, 9);

ALTER TABLE recipes AUTO_INCREMENT = 1;

INSERT INTO recipes(date_published, description, name, skill_level, url, user_id, instructions)
VALUES (CURDATE(),'This Cilantro Lime Rice is super easy to make and better than Chipotle\'s and super easy to make! It goes great with Asian or Mexican food',
        'Cilantro & Lime Rice','easy', 'https://bit.ly/37MQieW',3,'..'
        ),
       (CURDATE(),'This grilled mushroom and swiss cheese burger is the best burger you''ll ever make and it''s fairly simple.',
        'Grilled Mushroom & Swiss Burger','easy','https://bit.ly/35I9Jmo',3,'..'),
       (CURDATE(),'Cacio e Pepe is a pasta dish from modern Roman cuisine. "Cacio e pepe" means "cheese and pepper" in several central Italian dialects.',
        'Cacio e Pepe','beginner','https://bit.ly/31KeydW',3,'..'),
       (CURDATE(),'Do you love the flavor of saucy barbecue chicken but not the hours spent tending to the smoker? We’ve got you.',
        'Smokehouse Pulled Chicken Bowls','beginner','../images/smokehouse-pulled-chicken-bowls-e6772b98.jpg',3,'..'),
       (CURDATE(),'FreshFam, meet bavette steak! This lesser-known but ultra-flavorful cut of beef is sure to be your new fave. Grill it with salt and coarsely ground black pepper, top it with a zippy chimichurri, whip up some sizzling steak-centric fajitas, and on and on.',
        'Bavette Steak','easy','https://cdn.filestackcontent.com/C4MRw9N6RqqfHqiSr63U',3,'..'),
       (CURDATE(),'This recipe is here to blow up everything you think you know about meatballs. All dramatics aside, these meatballs are truly game-changing thanks to a certain special sauce.',
        'Firecracker Meatballs','medium','https://cdn.filestackcontent.com/1hzxxklTSaR18Z97s5fw',3,'..'),
       (CURDATE(),'A big bowl of pasta never fails to satisfy—it’s quick, carby, comforting, and our go-to no matter the weather.',
        'Pork Sausage Spaghetti Bolognese','hard','https://cdn.filestackcontent.com/kiu2VaqRQGGPZcJaPbHJ',3,'..'),
       (CURDATE(),'Making a great sandwich is an art form. Dramatics aside—when you get it right, there’s really nothing better!',
        'Prosciutto Caprese Sandwiches','easy','https://cdn.filestackcontent.com/lSIcHIAKROSlwiDtmWP5',3,'..'),
       (CURDATE(),'Here is some awesome salmon seasoned with thyme that only takes about 20 minutes to cook.',
        'Pan-Fried Thyme Salmon','medium','https://cdn.filestackcontent.com/oyIC5pnOTbCtVo2M3DVQ',3,'..'),
       (CURDATE(),'It’s hard NOT to love any ingredient when it’s fritter-fied, aka covered in batter and cooked until perfectly golden. ',
        'Spiced Chickpea Fritters','easy','https://cdn.filestackcontent.com/gtzhifs4QSyftw9QfVgQ', 4,'..'),
       (CURDATE(),'This Cilantro Lime Rice is super easy to make and better than Chipotle''s and super easy to make! It goes great with Asian or Mexican food',
        'Cilantro & Lime Rice','medium','https://cdn.filestackcontent.com/wEUHH7IET6aNjW3TfFdB', 4,'..');

