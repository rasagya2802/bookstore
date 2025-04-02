Please follow the below steps after cloning.

Step 1:(In MySql)
Create DATABASE bookstore;

Step 2:(In MySql)
USE bookstore;

Step 3:(In MySql)
INSERT INTO book (id, description, image_url, title, price) VALUES 
(1,'A classic novel.','/images/gatsby.jpg','The Great Gatsby',400),
(2,'A dystopian novel.', '/images/1984.jpg','1984', 300 ),
(3, 'A classic novel.', '/images/pride_and_prejudice.jpg', 'Pride and Prejudice', 349),
(4, 'A dystopian novel.', '/images/brave_new_world.jpg', 'Brave New World', 459),
(5, 'A fantasy novel.', '/images/lord_of_the_rings.jpg', 'The Lord of the Rings', 599),
(6, 'A classic novel.', '/images/to_kill_a_mockingbird.jpg', 'To Kill a Mockingbird', 399),
(7, 'A dystopian novel.', '/images/fahrenheit_451.jpg', 'Fahrenheit 451', 499),
(8, 'A magical realism novel.', '/images/one_hundred_years_of_solitude.jpg', 'One Hundred Years of Solitude', 549),
(9, 'A classic novel.', '/images/jane_eyre.jpg', 'Jane Eyre', 399),
(10, 'A dystopian novel.', '/images/the_handmaids_tale.jpg', 'The Handmaid\'s Tale', 479),
(11, 'A classic novel.', '/images/the_picture_of_dorian_gray.jpg', 'The Picture of Dorian Gray', 429),
(12, 'A fantasy novel.', '/images/the_hobbit.jpg', 'The Hobbit', 499);

Step 4:(Executing the code in VSCode):
Right click the file src/main/java/com/example/bookstore/BookstoreApplication.java
Start debugging

