-- Insert into person
insert into persons (id, first_name, last_name, email, phone)
values (1, 'Bart', 'Brosens', 'bart@gmail.com', '0618031976'),
       (2, 'Mark', 'Schrauwen', 'mark@gmail.com', '0623061985'),
       (3, 'Barry', 'Stuijts', 'barry@gmail.com', '0619081979'),
       (4, 'Olaf', 'van den Berg', 'olaf@gmail.com', '0615111984'),
       (5, 'Stefan', 'Verster', 'stefan@gmail.com', '0624041982'),
       (6, 'Abi', 'Borcic', 'abi@gmail.com', '0663547483'),
       (7, 'Rens', 'Geubbels', 'rens@gmail.com', '0649724952'),
       (8, 'Arne', 'Martens', 'arne@gmail.com', '0656787834'),
       (9, 'Erik', 'Griffioen', 'erik@gmail.com', '0699834756'),
       (10, 'Henning', 'van Laarhoven', 'henning@gmail.com', '0674673392'),
       (11, 'Suzanne', 'van der Leeden', 'suzanne@soundrental.nl', '0615051965'),
       (12, 'Fien', 'Verster', 'fien@soundrental.nl', '0612281996'),
       (13, 'Elise', 'Verheyden', 'elise@soundrental.nl', '0612281996');


-- Insert into instrument
insert into instruments (id, name)
values (1, 'lead vocals'),
       (2, 'backing vocals'),
       (3, 'electric bass guitar'),
       (4, 'acoustic guitar'),
       (5, 'electric guitar'),
       (6, 'blues harp'),
       (7, 'drum kit');

-- Insert into event
insert into events (id, date, venue)
values (1, '2027-07-23', 'cafe de Bommel'),
       (2, '2027-06-29', 'cafe de Kerel'),
       (3, '2027-08-02', 'Electron');

-- Insert into act
insert into acts (id, name, phone, email)
values (1, 'Rita Lynn', '0645188510', 'info@ritalynn.zu'),
       (2, 'R.B. Pine', '0663547483', 'info@rbpine.nl'),
       (3, 'Blabber n Smoke', '0653889223', 'info@blabbernsmoke.nl');

-- Insert into equipment
insert into equipment (id, brand, model)
values (1, 'Shure', 'SM58'),
       (2, 'Shure', 'SM58'),
       (3, 'Shure', 'SM58'),
       (4, 'Shure', 'SM58'),
       (5, 'Shure', 'SM57'),
       (6, 'Shure', 'SM57'),
       (7, 'Shure', 'Beta 52A'),
       (8, 'Shure', 'Beta 52A'),
       (9, 'Sennheiser', 'e904'),
       (10, 'Sennheiser', 'e904'),
       (11, 'Sennheiser', 'e904'),
       (12, 'Sennheiser', 'e904'),
       (13, 'Sennheiser', 'e906'),
       (14, 'Sennheiser', 'e906'),
       (15, 'AKG', 'C414 XLS'),
       (16, 'AKG', 'C414 XLS'),
       (17, 'AKG', 'C411 PP'),
       (18, 'AKG', 'C411 PP'),
       (19, 'Neumann', 'KM 184'),
       (20, 'Neumann', 'KM 184'),
       (21, 'Mackie', 'ProFX22v3'),
       (22, 'Mackie', 'Onyx24'),
       (23, 'Mackie', '1604 VLZ4');


-- Insert into microphones
insert into microphones (id, phantom_required)
values (1, false),
       (2, false),
       (3, false),
       (4, false),
       (5, false),
       (6, false),
       (7, false),
       (8, false),
       (9, false),
       (10, false),
       (11, false),
       (12, false),
       (13, false),
       (14, false),
       (15, true),
       (16, true),
       (17, true),
       (18, true),
       (19, true),
       (20, true);

-- Insert into microphone_polar_patterns
insert into microphone_polar_patterns (microphone_id, polar_pattern)
values (1, 'CARDIOID'),
       (2, 'CARDIOID'),
       (3, 'CARDIOID'),
       (4, 'CARDIOID'),
       (5, 'CARDIOID'),
       (6, 'CARDIOID'),
       (7, 'CARDIOID'),
       (8, 'CARDIOID'),
       (9, 'CARDIOID'),
       (10, 'CARDIOID'),
       (11, 'CARDIOID'),
       (12, 'CARDIOID'),
       (13, 'CARDIOID'),
       (14, 'CARDIOID'),
       (15, 'CARDIOID'),
       (15, 'OMNIDIRECTIONAL'),
       (15, 'FIGURE_8'),
       (16, 'CARDIOID'),
       (16, 'OMNIDIRECTIONAL'),
       (16, 'FIGURE_8'),
       (17, 'FIGURE_8'),
       (18, 'FIGURE_8'),
       (19, 'CARDIOID'),
       (20, 'CARDIOID');


-- Insert into mixers
insert into mixers (id)
values (21),
       (22),
       (23);


-- Insert into performerProfile (needs person)
insert into performer_profiles (id, person_id)
values (1, 1),
       (2, 2),
       (3, 3),
       (4, 4),
       (5, 5),
       (6, 6),
       (7, 7),
       (8, 8),
       (9, 9),
       (10, 10);

-- Insert into employeeProfile (needs person)
insert into employee_profiles (id, person_id, drivers_license)
values (1,11,'AUTO'),
       (2,12,'VRACHTWAGEN'),
       (3,13,'KLEINE_BUS'),
       (4,6,'AUTO');

-- Insert into performer-instrument (needs performer-profile + instrument)
insert into performer_instruments (id, performer_profile_id, instrument_id)
values (1, 1, 1),
       (2, 1, 4),
       (3, 1, 5),
       (4, 2, 2),
       (5, 2, 6),
       (6, 3, 2),
       (7, 3, 3),
       (8, 4, 4),
       (9, 4, 5),
       (10, 5, 7),
       (11, 6, 1),
       (12, 6, 5),
       (13, 7, 3),
       (14, 8, 2),
       (15, 8, 5),
       (16, 9, 1),
       (17, 9, 6),
       (18, 10, 3);

-- Insert into source (needs performer-instrument)
insert into sources (id, performer_instrument_id, name)
values (1, 1, 'lead vocals Bart'),
       (2, 2, 'acoustic guitar Bart'),
       (3, 3, 'electric guitar Bart'),
       (4, 4, 'bgv Mark'),
       (5, 5, 'blues harp left Mark'),
       (6, 5, 'blues harp right Mark'),
       (7, 6, 'bgv Barry'),
       (8, 7, 'electric bass Barry'),
       (9, 8, 'acoustic guitar Olaf'),
       (10, 9, 'electric guitar Olaf'),
       (11, 10, 'kick'),
       (12, 10, 'snare'),
       (13, 10, 'rack tom'),
       (14, 10, 'floor tom'),
       (15, 10, 'hihat'),
       (16, 10, 'overhead left'),
       (17, 10, 'overhead right'),
       (18, 11, 'lead vocals Abi'),
       (19, 12, 'electric guitar Abi'),
       (20, 13, 'electric bass Rens'),
       (21, 14, 'bgv Arne'),
       (22, 15, 'electric guitar Arne'),
       (23, 16, 'lead vocals Erik'),
       (24, 17, 'blues harp Erik'),
       (25, 18, 'electric bass Henning');


-- Insert into channel (needs mixer + optional source)
insert into channels (id, mixer_id, number, source_id)
values (1, 21, 1, 11),
       (2, 21, 2, 12),
       (3, 21, 3, 13),
       (4, 21, 4, 14),
       (5, 21, 5, 15),
       (6, 21, 6, 16),
       (7, 21, 7, 17),
       (8, 21, 8, 20),
       (9, 21, 9, 18),
       (10, 21, 10, 19),
       (11, 21, 11, null),
       (12, 21, 12, null),
       (13, 21, 13, null),
       (14, 21, 14, null),
       (15, 21, 15, null),
       (16, 21, 16, null),
       (17, 21, 17, null),
       (18, 22, 1, 11),
       (19, 22, 2, 12),
       (20, 22, 3, 13),
       (21, 22, 4, 14),
       (22, 22, 5, 15),
       (23, 22, 6, 16),
       (24, 22, 7, 17),
       (25, 22, 8, 8),
       (26, 22, 9, 1),
       (27, 22, 10, 4),
       (28, 22, 11, 7),
       (29, 22, 12, 2),
       (30, 22, 13, 3),
       (31, 22, 14, 9),
       (32, 22, 15, 10),
       (33, 22, 16, 5),
       (34, 22, 17, 6),
       (35, 22, 18, null),
       (36, 23, 1, 11),
       (37, 23, 2, 12),
       (38, 23, 3, 13),
       (39, 23, 4, 14),
       (40, 23, 5, 15),
       (41, 23, 6, 16),
       (42, 23, 7, 17),
       (43, 23, 8, 25),
       (44, 23, 9, 23),
       (45, 23, 10, 21),
       (46, 23, 11, 24),
       (47, 23, 12, 22),
       (48, 23, 13, null),
       (49, 23, 14, null),
       (50, 23, 15, null),
       (51, 23, 16, null);

-- Insert into performance (needs event + act)
insert into performances (id, event_id, act_id)
values (1, 1, 2),
       (2, 2, 1),
       (3, 3, 3);

-- Insert into performer-act (needs performer-profile + act)
insert into performer_acts (id, performer_id, act_id)
values (1, 1, 1),
       (2, 2, 1),
       (3, 3, 1),
       (4, 4, 1),
       (5, 5, 1),
       (6, 5, 2),
       (7, 5, 3),
       (8, 6, 2),
       (9, 7, 2),
       (10, 8, 3),
       (11, 9, 3),
       (12, 10, 3);

-- Insert into performer_act_roles
insert into performer_act_roles (performer_act_id, role)
values (1, 'lead_singer'),
       (1, 'guitar_player'),
       (2, 'blues_harp_player'),
       (2, 'background_singer'),
       (3, 'bass_player'),
       (3, 'background_singer'),
       (4, 'guitar_player'),
       (5, 'drummer'),
       (6, 'drummer'),
       (7, 'drummer'),
       (8, 'lead_singer'),
       (8, 'guitar_player'),
       (9, 'bass_player'),
       (10, 'guitar_player'),
       (10, 'background_singer'),
       (11, 'lead_singer'),
       (11, 'blues_harp_player'),
       (12, 'bass_player');



-- Insert into event-assignment (event + person)
insert into event_assignments (id, event_id, person_id, event_role)
values (1, 1, 11, 'FOH_ENGINEER'),
       (2, 1, 12, 'MONITOR_ENGINEER'),
       (3, 1, 13, 'STAGE_TECH'),
       (4, 2, 11, 'MONITOR_ENGINEER'),
       (5, 2, 12, 'STAGE_TECH'),
       (6, 2, 13, 'FOH_ENGINEER'),
       (7, 3, 11, 'STAGE_TECH'),
       (8, 3, 12, 'FOH_ENGINEER'),
       (9, 3, 13, 'MONITOR_ENGINEER');


-- Insert into equipment-event-assignment (needs event + equipment)
insert into equipment_event_assignments (id, equipment_id, event_id)
values (1,22,2),
       (2,7,2),
       (3,5,2),
       (4,9,2),
       (5,10,2),
       (6,19,2),
       (7,15,2),
       (8,16,2),
       (9,8,2),
       (10,1,2),
       (11,2,2),
       (12,3,2),
       (13,17,2),
       (14,13,2),
       (15,18,2),
       (16,14,2),
       (17,21,1),
       (18,7,1),
       (19,5,1),
       (20,9,1),
       (21,10,1),
       (22,19,1),
       (23,15,1),
       (24,16,1),
       (25,8,1),
       (26,1,1),
       (27,13,1),
       (28,23,3),
       (29,7,3),
       (30,5,3),
       (31,9,3),
       (32,10,3),
       (33,19,3),
       (34,15,3),
       (35,16,3),
       (36,8,3),
       (37,1,3),
       (38,2,3),
       (39,3,3),
       (40,13,3);

-- Synchronize sequences
SELECT setval('acts_id_seq', (SELECT MAX(id) FROM acts));
SELECT setval('events_id_seq', (SELECT MAX(id) FROM events));
SELECT setval('performer_profiles_id_seq', (SELECT MAX(id) FROM performer_profiles));
SELECT setval('persons_id_seq', (SELECT MAX(id) FROM persons));
SELECT setval('instruments_id_seq', (SELECT MAX(id) FROM instruments));
SELECT setval('employee_profiles_id_seq', (SELECT MAX(id) FROM employee_profiles));
SELECT setval('performer_acts_id_seq', (SELECT MAX(id) FROM performer_acts));
SELECT setval('performances_id_seq', (SELECT MAX(id) FROM performances));
SELECT setval('sources_id_seq', (SELECT MAX(id) FROM sources));
SELECT setval('channels_id_seq', (SELECT MAX(id) FROM channels));
SELECT setval('equipment_id_seq', (SELECT MAX(id) FROM equipment));
SELECT setval('performer_profiles_id_seq', (SELECT MAX(id) FROM performer_profiles));
SELECT setval('performer_instruments_id_seq', (SELECT MAX(id) FROM performer_instruments));
SELECT setval('event_assignments_id_seq', (SELECT MAX(id) FROM event_assignments));
SELECT setval('equipment_event_assignments_id_seq', (SELECT MAX(id) FROM equipment_event_assignments));
