INSERT INTO CATEGORY (CATEGORY_ID,CATEGORY_NAME) VALUES 
(1,'Fashion'),
(2,'Electronics'),
(3,'Books'),
(4,'Groceries'),
(5,'Medicines');

INSERT INTO ROLE (ROLE_ID,ROLE) VALUES 
(1,'CONSUMER'),
(2,'SELLER');

INSERT INTO CLIENT (USER_ID,PASSWORD,USERNAME) VALUES 
(1,'pass_word','jack'),
(2,'pass_word','bob'),
(3,'pass_word','apple'),
(4,'pass_word','glaxo');

INSERT INTO CART (CART_ID,TOTAL_AMOUNT,USER_ID) VALUES 
(1,20,1),
(2,0,2);

INSERT INTO USER_ROLES (USER_ID,ROLE_ID) VALUES 
(1,1),
(2,1),
(3,2),
(4,2);

INSERT INTO PRODUCT (PRODUCT_ID,PRICE,PRODUCT_NAME, CATEGORY_ID,SELLER_ID) VALUES 
(1,29190, 'Apple iPad 10.2 8th Gen WiFi iOS Tablet', 2, 3),
(2,10, 'Crocin pain relief tablet', 5, 4);

INSERT INTO CART_PRODUCT (CP_ID,CART_ID,PRODUCT_ID,QUANTITY) VALUES (1,1,2,2);