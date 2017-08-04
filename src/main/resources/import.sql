-- Insert roles
insert into role (name) values ('ROLE_USER');

-- Insert users
insert into user (email, password, first_name, last_name, enabled, role_id) values ('test1@ea.com', '$2a$10$eyqT2KN/JGsJwDf6C4WRpOJoS7HbHEfxmkANLLCh/2TA.tpqKydBa', "test", "1", true,1);
insert into user (email, password, first_name, last_name, enabled, role_id) values ('test2@ea.com', '$2a$10$eyqT2KN/JGsJwDf6C4WRpOJoS7HbHEfxmkANLLCh/2TA.tpqKydBa', "test", "2", true,1);

-- Insert posts
insert into post (title,description, price, buy, email, user_id) values ("German Military Bag", "Leather military bag case, Dresden, 1953. Perfect condition. Pick up downtown Vancouver.", 25, true, 'test2@ea.com', 2);
insert into post (title,description, price, buy, email, user_id) values ("Antique Portico Clock", "Available is an original Biedermeier Mantel Striking Portico Clock with a square brass movement, spring driven, power reserve with repeater action: 3 day, Viennese 4/4 hour striking mechanism on chime springs, Silk thread suspension, Porcelain dial, blued steel clock hands, chased bezel, steel rod pendulum, ", 204, false, 'test1@ea.com', 1);
insert into post (title,description, price, buy, email, user_id) values ("Dog crate - Large", "Large dog crate. Comes with wheels for easier transport Approx size is 30 inches long, 24 inches tall and 20 inches wide $80 Pick up in Richmond or south Burnaby", 25, true, 'test1@ea.com', 1);
insert into post (title,description, price, buy, email, user_id) values ("German Military Bag Case, 1953 year", "Leather military bag case, Dresden, 1953. Perfect condition. Pick up downtown Vancouver.", 25, true, 'test2@ea.com', 2);
insert into post (title,description, price, buy, email, user_id) values ("Mexican Food", "Delicious Tacos ", 504, false, 'test1@ea.com', 1);
insert into post (title,description, price, buy, email, user_id) values ("Size 12 Work Shoes", "Bought these size 12 Steeltoes at Mark Works Wearhouse for $100 before tax. I wore them once for a job that didnt work out, and as we all know the return policy at Marks is garbage. They are in perfect condition. ", 100, false, 'test2@ea.com', 2);
insert into post (title,description, price, buy, email, user_id) values ("Early Copper Tub", "This is a nice condition solid copper oval tub. Not negotiable.,", 25, true, 'test1@ea.com', 1);
insert into post (title,description, price, buy, email, user_id) values ("German Military Bag Case, 1953 year", "Leather military bag case, Dresden, 1953. Perfect condition. Pick up downtown Vancouver.", 25, true, 'test2@ea.com', 2);
insert into post (title,description, price, buy, email, user_id) values ("Marcus Water Bottle", "Really big stainless steel ", 204, false, 'test1@ea.com', 1);
insert into post (title,description, price, buy, email, user_id) values ("Decanter", "porcelain decanter Signed. 13 inch tall Bought them for $150 each in 1993 North van", 100, false, 'test2@ea.com', 2);
insert into post (title,description, price, buy, email, user_id) values ("19th Century Jug", "This is a early salt glazed Jug from the early 1800s or before. Has a original Makers seal. A few small nicks. Minor for such a early sealed bottle. Not negotiable.", 100, false, 'test2@ea.com', 2);
insert into post (title,description, price, buy, email, user_id) values ("Soroush Weights", "5 pound dumbells, I don't lift a lot so i want to get rid of them", 200, true, 'test1@ea.com', 1);