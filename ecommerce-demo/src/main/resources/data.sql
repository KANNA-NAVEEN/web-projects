INSERT INTO CATEGORY (CATEGORY_NAME) VALUES 
('Fashion'),
('Electronics'),
('Books'),
('Groceries'),
('Medicines');

INSERT INTO CLIENT (PASSWORD,USERNAME) VALUES 
('pass_word','jack'),
('pass_word','bob'),
('pass_word','apple'),
('pass_word','glaxo');

INSERT INTO CART (TOTAL_AMOUNT,USER_USER_ID) VALUES 
(20,1),
(0,2);

INSERT INTO USER_ROLE (USER_ID,ROLES) VALUES 
(1,'CONSUMER'),
(2,'CONSUMER'),
(3,'SELLER'),
(4,'SELLER');

INSERT INTO PRODUCT (PRICE,PRODUCT_NAME, CATEGORY_ID,SELLER_ID) VALUES 
(29190, 'Apple iPad 10.2 8th Gen WiFi iOS Tablet', 2, 3),
(10, 'Crocin pain relief tablet', 5, 4);

INSERT INTO CART_PRODUCT (CART_ID,PRODUCT_ID,QUANTITY) VALUES (1,2,2);