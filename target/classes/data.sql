INSERT INTO users(email, enabled, first_name,last_name,password, username)
values('nikola@gmail.com', true, 'Nikola','Todorovic','$2a$10$TR6FeThOZO9HIbdD/OG7o.GC/B1SyqU1YrHHHsKdCMo6KzIuZhRPG', 'nikola123');
INSERT INTO users(email, enabled, first_name,last_name,password, username)
values('boki@gmail.com', true, 'Bogdan','Blagojevic','$2a$10$TR6FeThOZO9HIbdD/OG7o.GC/B1SyqU1YrHHHsKdCMo6KzIuZhRPG', 'boki123');
INSERT INTO users(email, enabled, first_name,last_name,password, username)
values('nikolina@gmail.com', true, 'Nikolina','Sesa','$2a$10$TR6FeThOZO9HIbdD/OG7o.GC/B1SyqU1YrHHHsKdCMo6KzIuZhRPG', 'sesa123');
INSERT INTO users(email, enabled, first_name,last_name,password, username)
values('nenad@gmail.com', true, 'Nenad','Joldic','$2a$10$TR6FeThOZO9HIbdD/OG7o.GC/B1SyqU1YrHHHsKdCMo6KzIuZhRPG', 'cone123');
INSERT INTO users(email, enabled, first_name,last_name,password, username)
values('srdjan@gmail.com', true, 'Srdjan','Tosic','$2a$10$TR6FeThOZO9HIbdD/OG7o.GC/B1SyqU1YrHHHsKdCMo6KzIuZhRPG', 'tole123');
INSERT INTO users(email, enabled, first_name,last_name,password, username)
values('marko@gmail.com', true, 'Marko','Markovic','$2a$10$TR6FeThOZO9HIbdD/OG7o.GC/B1SyqU1YrHHHsKdCMo6KzIuZhRPG', 'marko123');
INSERT INTO users(email, enabled, first_name, last_name,password, username)
values('ljiljana@gmail.com', true, 'Ljiljana','Trivunovic','$2a$10$TR6FeThOZO9HIbdD/OG7o.GC/B1SyqU1YrHHHsKdCMo6KzIuZhRPG', 'ljilja123');
INSERT INTO users(email, enabled, first_name, last_name,password, username)
values('bojana222@gmail.com', true, 'Bojana','Grujic','$2a$10$TR6FeThOZO9HIbdD/OG7o.GC/B1SyqU1YrHHHsKdCMo6KzIuZhRPG', 'bojana123');
INSERT INTO users(email, enabled, first_name, last_name,password, username)
values('marko123@gmail.com', true, 'Marko','Markovic','$2a$10$TR6FeThOZO9HIbdD/OG7o.GC/B1SyqU1YrHHHsKdCMo6KzIuZhRPG', 'markoMarkovic123');
INSERT INTO users(email, enabled, first_name, last_name,password, username)
values('milan@gmail.com', true, 'Milan','Milic','$2a$10$TR6FeThOZO9HIbdD/OG7o.GC/B1SyqU1YrHHHsKdCMo6KzIuZhRPG', 'milanMilic123');

INSERT INTO ROLE (name) VALUES ('ROLE_SYSTEM_ADMIN');
INSERT INTO ROLE (name) VALUES ('ROLE_SUPPLY_MANAGER');
INSERT INTO ROLE (name) VALUES ('ROLE_DELIVERY_MANAGER');
INSERT INTO ROLE (name) VALUES ('ROLE_PRODUCTION_MANAGER');
INSERT INTO ROLE (name) VALUES ('ROLE_SALE_MANAGER');
INSERT INTO ROLE (name) VALUES ('ROLE_REGULAR_USER');
INSERT INTO ROLE (name) VALUES ('ROLE_DRIVER');

INSERT INTO user_role(user_id, role_id)
values(1,1);
INSERT INTO user_role(user_id, role_id)
values(2,2);
INSERT INTO user_role(user_id, role_id)
values(3,3);
INSERT INTO user_role(user_id, role_id)
values(4,4);
INSERT INTO user_role(user_id, role_id)
values(5,5);
INSERT INTO user_role(user_id, role_id)
values(6,6);
INSERT INTO user_role(user_id, role_id)
values(9,7);
INSERT INTO user_role(user_id, role_id)
values(10,7);

INSERT INTO products(product_name, price, weight) values( 'Chocolate', 300.00, 0.0001);
INSERT INTO products( product_name, price, weight) values( 'Cookie', 400.00, 0.0001);
INSERT INTO products( product_name, price, weight) values( 'Candy', 800.00, 0.0001);

INSERT INTO drivers(adress, first_name, jmbg, last_name, phone_number, deleted, username, email) values('Pariske komune 45, Novi Sad', 'Marko', '2009999506022', 'Markovic', '0654562130', false, 'markoMarkovic123', 'marko123@gmail.com');
INSERT INTO drivers(adress, first_name, jmbg, last_name, phone_number, deleted, username, email) values('Fruskogorska 3, Novi Sad', 'Milan', '1203999203066', 'Milic', '0694568596', false, 'milanMilic123', 'milan@gmail.com');
INSERT INTO drivers(adress, first_name, jmbg, last_name, phone_number, deleted, username, email) values('Partizanska 13, Zabalj', 'Branko', '0306996203011', 'Brkic', '0632587595', false, 'brankoBrkic123', 'branko@gmail.com');
INSERT INTO drivers(adress, first_name, jmbg, last_name, phone_number, deleted, username, email) values('Nikole Tesle 22, Zabalj', 'Dragan', '0206997506022', 'Dragic', '0678569685', false, 'draganDragic123', 'dragan@gmail.com');
INSERT INTO drivers(adress, first_name, jmbg, last_name, phone_number, deleted, username, email) values('Tone Hadzica 3, Novi Sad', 'Bojan', '2503996405033', 'Bojic', '0645231263', false, 'bojanBojic123', 'bojan@gmail.com');
INSERT INTO drivers(adress, first_name, jmbg, last_name, phone_number, deleted, username, email) values('Karadjordjeva 23, Djurdjevo', 'Pera', '3005998203011', 'Peric', '0658975263', false, 'peraPeric123', 'pera@gmail.com');

INSERT INTO trucks(capacity, driveability, name, registration_number, deleted) values(2.0, true, 'Fiat', 'NS123-BB', false);
INSERT INTO trucks(capacity, driveability, name, registration_number, deleted) values(3.0, true, 'Citroen', 'NS020-SD', false);
INSERT INTO trucks(capacity, driveability, name, registration_number, deleted) values(2.0, true, 'Citroen', 'NS222-DD', false);
INSERT INTO trucks(capacity, driveability, name, registration_number, deleted) values(3.5, true, 'Mercedes', 'NS636-KJ', false);
INSERT INTO trucks(capacity, driveability, name, registration_number, deleted) values(3.5, true, 'Mercedes', 'NS587-MN', false);

INSERT INTO driver_category(category_mark, max_load_capacity) VALUES('B', 2.0);
INSERT INTO driver_category(category_mark, max_load_capacity) VALUES('C1', 3.0);
INSERT INTO driver_category(category_mark, max_load_capacity) VALUES('C', 7.5);
INSERT INTO driver_category(category_mark, max_load_capacity) VALUES('F', 1.0);

INSERT INTO driver_categories(category_id, driver_id) VALUES(1, 1);
INSERT INTO driver_categories(category_id, driver_id) VALUES(1, 2);
INSERT INTO driver_categories(category_id, driver_id) VALUES(1, 3);
INSERT INTO driver_categories(category_id, driver_id) VALUES(1, 4);
INSERT INTO driver_categories(category_id, driver_id) VALUES(1, 5);
INSERT INTO driver_categories(category_id, driver_id) VALUES(1, 6);
INSERT INTO driver_categories(category_id, driver_id) VALUES(3, 1);
INSERT INTO driver_categories(category_id, driver_id) VALUES(4, 1);
INSERT INTO driver_categories(category_id, driver_id) VALUES(2, 2);
INSERT INTO driver_categories(category_id, driver_id) VALUES(3, 2);
INSERT INTO driver_categories(category_id, driver_id) VALUES(4, 3);
INSERT INTO driver_categories(category_id, driver_id) VALUES(2, 4);
INSERT INTO driver_categories(category_id, driver_id) VALUES(4, 5);
INSERT INTO driver_categories(category_id, driver_id) VALUES(4, 6);

INSERT INTO engagements(driver_id, truck_id, engagement_start_date, engagement_end_date) values (1, 2, '2023-04-16', NULL);
INSERT INTO engagements(driver_id, truck_id, engagement_start_date, engagement_end_date) values (2, 1, '2023-05-02', NULL);

INSERT INTO tours(city, date) VALUES ('Novi Sad', '2023-06-08');
INSERT INTO tours(city, date) VALUES ('Sremska Mitrovica', '2023-06-08');
INSERT INTO tours(city, date) VALUES ('Djurdjevo', '2023-05-25');
INSERT INTO tours(city, date) VALUES ('Novi Sad', '2023-06-02');

INSERT INTO tour_engagements(engagement_id, tour_id) VALUES(1, 1);
INSERT INTO tour_engagements(engagement_id, tour_id) VALUES(2, 2);
INSERT INTO tour_engagements(engagement_id, tour_id) VALUES(2, 3);
INSERT INTO tour_engagements(engagement_id, tour_id) VALUES(2, 4);

INSERT INTO orders(address, city, date, total_price, total_weight, status, priority, regular_user_id, tour_id) values('Nikole Tesle 33', 'Novi Sad', '05-06-2023', 1000, 0.0003, 'Ready_For_Delivery', false, 6, 1);
INSERT INTO orders(address, city, date, total_price, total_weight, status, priority, regular_user_id, tour_id) values('Fruskogorska 3', 'Novi Sad', '06-06-2023', 2100, 0.0006, 'Ready_For_Delivery', false, 7, 1);
INSERT INTO orders(address, city, date, total_price, total_weight, status, priority) values('Micurinova 72', 'Novi Sad', '08-05-2023', 3000, 0.0006, 'Approved', false);
INSERT INTO orders(address, city, date, total_price, total_weight, status, priority, regular_user_id, tour_id) values('Ive Andrica 80', 'Sremska Mitrovica', '03-06-2023', 1200, 0.0003, 'Ready_For_Delivery', false, 6, 2);
INSERT INTO orders(address, city, date, total_price, total_weight, status, priority, regular_user_id, tour_id) values('Fruskogorska 33', 'Sremska Mitrovica', '03-06-2023', 900, 0.0003, 'Ready_For_Delivery', false, 7, 2);
INSERT INTO orders(address, city, date, total_price, total_weight, status, priority, regular_user_id, tour_id) values('Nikole Tesle 45', 'Djurdjevo', '05-06-2023', 1600, 0.0003, 'Delivered', false, 8, 3);
INSERT INTO orders(address, city, date, total_price, total_weight, status, priority, regular_user_id, tour_id) values('Jevrejska 6', 'Novi Sad', '03-06-2023', 1600, 0.0003, 'Delivered', false, 7, 4);
INSERT INTO orders(address, city, date, total_price, total_weight, status, priority, regular_user_id, tour_id) values('Brace Dronjak 67', 'Novi Sad', '04-06-2023', 2400, 0.0004, 'Delivered', false, 8, 4);
INSERT INTO orders(address, city, date, total_price, total_weight, status, priority, regular_user_id, tour_id) values('Rumenacka 136', 'Novi Sad', '05-06-2023', 2400, 0.0004, 'Delivered', false, 7, 4);
INSERT INTO orders(address, city, date, total_price, total_weight, status, priority, regular_user_id, tour_id) values('Nikole Tesle 45', 'Djurdjevo', '10-06-2023', 1000, 0.0003, 'Completed', false, 6, null);
INSERT INTO orders(address, city, date, total_price, total_weight, status, priority, regular_user_id, tour_id) values('Marka Kraljevica 13', 'Djurdjevo', '11-06-2023', 1000, 0.0003, 'Completed', false, 7, null);

INSERT INTO ordered_product(order_id, product_id, quantity) values (1, 1, 2);
INSERT INTO ordered_product(order_id, product_id, quantity) values (1, 2, 1);
INSERT INTO ordered_product(order_id, product_id, quantity) values (2, 1, 3);
INSERT INTO ordered_product(order_id, product_id, quantity) values (2, 2, 3);

INSERT INTO ordered_product(order_id, product_id, quantity) values (3, 1, 20);
INSERT INTO ordered_product(order_id, product_id, quantity) values (3, 2, 25);
INSERT INTO ordered_product(order_id, product_id, quantity) values (3, 3, 15);

INSERT INTO ordered_product(order_id, product_id, quantity) values (4, 2, 3);
INSERT INTO ordered_product(order_id, product_id, quantity) values (5, 1, 3);
INSERT INTO ordered_product(order_id, product_id, quantity) values (6, 3, 3);
INSERT INTO ordered_product(order_id, product_id, quantity) values (7, 3, 3);
INSERT INTO ordered_product(order_id, product_id, quantity) values (8, 3, 4);
INSERT INTO ordered_product(order_id, product_id, quantity) values (9, 3, 4);
INSERT INTO ordered_product(order_id, product_id, quantity) values (10, 1, 2);
INSERT INTO ordered_product(order_id, product_id, quantity) values (10, 2, 1);
INSERT INTO ordered_product(order_id, product_id, quantity) values (11, 1, 2);
INSERT INTO ordered_product(order_id, product_id, quantity) values (11, 2, 1);

INSERT INTO ingredients( name) VALUES ('Sugar');
INSERT INTO ingredients( name) VALUES ('Milk');
INSERT INTO ingredients( name) VALUES ('Cocoa');
INSERT INTO ingredients( name) VALUES ('Honey');

insert into machines(name, input_quantity, state, working_days, type) values('M1', 400, 'FREE', 5, 'Chocolate');
insert into machines(name, input_quantity, state, working_days, type) values('M2', 500, 'FREE', 8, 'Cookie');
insert into machines(name, input_quantity, state, working_days, type) values('M3', 380, 'FREE', 7, 'Candy');

insert into machines(name, input_quantity, state, working_days, type) values('M4', 250, 'FREE', 10, 'Chocolate');
insert into machines(name, input_quantity, state, working_days, type) values('M5', 300, 'FREE', 5, 'Cookie');
insert into machines(name, input_quantity, state, working_days, type) values('M6', 450, 'FREE', 6, 'Candy');

insert into machines(name, input_quantity, state, working_days, type) values('M7', 300, 'FREE', 8, 'Chocolate');
insert into machines(name, input_quantity, state, working_days, type) values('M8', 350, 'FREE', 9, 'Cookie');
insert into machines(name, input_quantity, state, working_days, type) values('M9', 220, 'FREE', 6, 'Candy');


INSERT INTO companies(address, city, country, email, name, phone_number) VALUES ('Janka Cmelika', 'Novi Sad', 'Serbia', 'ingredion@gmail.com', 'Ingredion', '0635597614');
INSERT INTO companies(address, city, country, email, name, phone_number) VALUES ('Micurinova', 'Belgrade', 'Serbia', 'cargill@gmail.com', 'Cargill', '0645963405');
INSERT INTO companies(address, city, country, email, name, phone_number) VALUES ('Branka Bajica', 'Sombor', 'Serbia', 'dsm@gmail.com', 'DSM', '0610036573');
INSERT INTO companies(address, city, country, email, name, phone_number) VALUES ('Sumadijska', 'Novi Sad', 'Serbia', 'givaudan@gmail.com', 'Givaudan', '0626500499');

INSERT INTO company_stock(price, quantity, company_id, ingredient_id, expiring_date) VALUES (40, 120, 1, 2, '01-05-2028');
INSERT INTO company_stock(price, quantity, company_id, ingredient_id, expiring_date) VALUES (35, 110, 1, 1, '01-06-2024');
INSERT INTO company_stock(price, quantity, company_id, ingredient_id, expiring_date) VALUES (20, 240, 1, 3, '01-08-2024');
INSERT INTO company_stock(price, quantity, company_id, ingredient_id, expiring_date) VALUES (25, 180, 1, 4, '01-02-2025');

INSERT INTO company_stock(price, quantity, company_id, ingredient_id, expiring_date) VALUES (50, 115, 2, 2, '01-03-2025');
INSERT INTO company_stock(price, quantity, company_id, ingredient_id, expiring_date) VALUES (30, 140, 2, 1, '01-04-2026');
INSERT INTO company_stock(price, quantity, company_id, ingredient_id, expiring_date) VALUES (25, 210, 2, 3, '01-05-2024');
INSERT INTO company_stock(price, quantity, company_id, ingredient_id, expiring_date) VALUES (18, 220, 2, 4, '01-07-2025');

INSERT INTO company_stock(price, quantity, company_id, ingredient_id, expiring_date) VALUES (43, 90, 3, 2, '01-08-2025');
INSERT INTO company_stock(price, quantity, company_id, ingredient_id, expiring_date) VALUES (37, 80, 3, 1, '01-07-2026');
INSERT INTO company_stock(price, quantity, company_id, ingredient_id, expiring_date) VALUES (28, 180, 3, 3, '01-06-2025');
INSERT INTO company_stock(price, quantity, company_id, ingredient_id, expiring_date) VALUES (10, 220, 3, 4, '01-09-2027');

INSERT INTO company_stock(price, quantity, company_id, ingredient_id, expiring_date) VALUES (30, 150, 4, 2, '01-01-2027');
INSERT INTO company_stock(price, quantity, company_id, ingredient_id, expiring_date) VALUES (42, 260, 4, 1, '01-05-2026');
INSERT INTO company_stock(price, quantity, company_id, ingredient_id, expiring_date) VALUES (26, 170, 4, 3, '01-09-2025');
INSERT INTO company_stock(price, quantity, company_id, ingredient_id, expiring_date) VALUES (28, 130, 4, 4, '01-07-2025');

INSERT INTO contracts(confidentiality_and_non_disclosure, execution_date, is_terminate, purpose_of_the_agreement, scope_of_work, termination_clause, termination_date, terms_and_conditions, company_id)
VALUES (true, '01-07-2024', false, 'Prodaja sirovina',  'Prozivodnja i isporuka sirovina', 'Nezadovoljstvo neke strane', '01-07-2025', true, 1);
INSERT INTO contracts(confidentiality_and_non_disclosure, execution_date, is_terminate, purpose_of_the_agreement, scope_of_work, termination_clause, termination_date, terms_and_conditions, company_id)
VALUES (true, '01-10-2023', false, 'Prodaja sirovina', 'Prozivodnja i isporuka sirovina', 'Nezadovoljstvo neke strane', '01-10-2024', true, 2);

INSERT INTO contract_part(price, contract_id, ingredient_id) VALUES (35, 1, 1);
INSERT INTO contract_part(price, contract_id, ingredient_id) VALUES (20, 1, 2);
INSERT INTO contract_part(price, contract_id, ingredient_id) VALUES (15, 2, 4);

insert into recipes(product_id, ingredient_id, quantity) values(1, 1, 10);
insert into recipes(product_id, ingredient_id, quantity) values(1, 2, 15);
insert into recipes(product_id, ingredient_id, quantity) values(1, 3, 15);

insert into recipes(product_id, ingredient_id, quantity) values(2, 2, 10);
insert into recipes(product_id, ingredient_id, quantity) values(2, 3, 5);
insert into recipes(product_id, ingredient_id, quantity) values(2, 4, 20);

insert into recipes(product_id, ingredient_id, quantity) values(3, 1, 10);
insert into recipes(product_id, ingredient_id, quantity) values(3, 3, 10);
insert into recipes(product_id, ingredient_id, quantity) values(3, 4, 5);

insert into product_requests(quantity, request_flag, product_id) values(40, 1, 1);
insert into product_requests(quantity, request_flag, product_id) values(35, 1, 2);
insert into product_requests(quantity, request_flag, product_id) values(60, 1, 3);

INSERT INTO ingredient_request(creation_date, delivery_deadline_date, quantity, request_flag, ingredient_id) VALUES ('26-05-2023', '26-07-2023', 150, 0, 1);
INSERT INTO ingredient_request(creation_date, delivery_deadline_date, quantity, request_flag, ingredient_id) VALUES ('26-05-2023', '26-07-2023', 120, 0, 2);
INSERT INTO ingredient_request(creation_date, delivery_deadline_date, quantity, request_flag, ingredient_id) VALUES ('26-05-2023', '26-07-2023', 90, 0, 3);
insert into warehouses(name) values ('Magacin');

insert into warehouse_ingredients(warehouse_id, ingredient_id, quantity) values(1, 1, 5500);
insert into warehouse_ingredients(warehouse_id, ingredient_id, quantity) values(1, 2, 2900);
insert into warehouse_ingredients(warehouse_id, ingredient_id, quantity) values(1, 3, 4400);
insert into warehouse_ingredients(warehouse_id, ingredient_id, quantity) values(1, 4, 7500);


