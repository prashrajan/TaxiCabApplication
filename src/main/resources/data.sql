/**
 * CREATE Script for init of DB
 */

-- create 3 ONLINE cars 

insert into car (id, date_created, model_name, license_plate, car_type, engine_type, car_manufacturer, car_online_status, deleted, rating, seat_count) 
values (102, now(), 'x1', 'HH1455', 'CONVERTIBLE', 'GAS', 'BMW', 'ONLINE', false, 4, 4);

insert into car (id, date_created, model_name, license_plate, car_type, engine_type, car_manufacturer, car_online_status, deleted, rating, seat_count) 
values (105, now(), 'a1', 'HH1458', 'SEDAN', 'HYBRID', 'MERCEDES', 'ONLINE', false, 4, 2);

insert into car (id, date_created, model_name, license_plate, car_type, engine_type, car_manufacturer, car_online_status, deleted, rating, seat_count) 
values (106, now(), 'b1', 'HH1459', 'STATIONWAGAN', 'HYBRID', 'HONDA', 'ONLINE', false, 2, 5);

-- Create 3 OFFLINE cars
insert into car (id, date_created, model_name, license_plate, car_type, engine_type, car_manufacturer, car_online_status, deleted, rating, seat_count) 
values (101, now(), 'v1', 'HH1454', 'SEDAN', 'ELECTRIC', 'AUDI', 'OFFLINE', false, 3, 5);

insert into car (id, date_created, model_name, license_plate, car_type, engine_type, car_manufacturer, car_online_status, deleted, rating, seat_count) 
values (103, now(), 'y1', 'HH1456', 'SPORTS', 'GAS', 'VW', 'OFFLINE', false, 5, 2);

insert into car (id, date_created, model_name, license_plate, car_type, engine_type, car_manufacturer, car_online_status, deleted, rating, seat_count) 
values (104, now(), 'z1', 'HH1457', 'CONVERTIBLE', 'ELECTRIC', 'BMW', 'OFFLINE', false, 3, 4);


-- Create 3 OFFLINE drivers

insert into driver (id, date_created, deleted, online_status, password, username, car_selected) values (1, now(), false, 'OFFLINE',
'driver01pw', 'driver01', 0);

insert into driver (id, date_created, deleted, online_status, password, username, car_selected) values (2, now(), false, 'OFFLINE',
'driver02pw', 'driver02', 0);

insert into driver (id, date_created, deleted, online_status, password, username, car_selected) values (3, now(), false, 'OFFLINE',
'driver03pw', 'driver03', 0);


-- Create 3 ONLINE drivers

insert into driver (id, date_created, deleted, online_status, password, username, car_selected) values (4, now(), false, 'ONLINE',
'driver04pw', 'driver04', 102);

insert into driver (id, date_created, deleted, online_status, password, username, car_selected) values (5, now(), false, 'ONLINE',
'driver05pw', 'driver05', 105 );

insert into driver (id, date_created, deleted, online_status, password, username, car_selected) values (6, now(), false, 'ONLINE',
'driver06pw', 'driver06', 103);

-- Create 1 OFFLINE driver with coordinate(longitude=9.5&latitude=55.954)

insert into driver (id, coordinate, date_coordinate_updated, date_created, deleted, online_status, password, username, car_selected)
values
 (7,
 'aced0005737200226f72672e737072696e676672616d65776f726b2e646174612e67656f2e506f696e7431b9e90ef11a4006020002440001784400017978704023000000000000404bfa1cac083127', now(), now(), false, 'OFFLINE',
'driver07pw', 'driver07', 0);

-- Create 1 ONLINE driver with coordinate(longitude=9.5&latitude=55.954)

insert into driver (id, coordinate, date_coordinate_updated, date_created, deleted, online_status, password, username, car_selected)
values
 (8,
 'aced0005737200226f72672e737072696e676672616d65776f726b2e646174612e67656f2e506f696e7431b9e90ef11a4006020002440001784400017978704023000000000000404bfa1cac083127', now(), now(), false, 'ONLINE',
'driver08pw', 'driver08', 101);