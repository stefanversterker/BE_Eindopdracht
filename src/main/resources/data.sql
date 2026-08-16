-- Insert into person
    insert into persons (id, first_name, last_name, email, phone)
    values (1, 'Bart', 'Brosens', 'bart@gmail.com', '06 18031976'),
           (2, 'Mark', 'Schrauwen', 'mark@gmail.com', '06 23061985'),
           (3, 'Barry', 'Stuijts', 'barry@gmail.com', '06 19081979'),
           (4, 'Olaf', 'van den Berg', 'olaf@gmail.com', '06 15111984'),
           (5, 'Stefan', 'Verster', 'stefan@gmail.com' ,'06 24041982'),
           (6, 'Abi', 'Borcic', 'abi@gmail.com', '06 63547483'),
           (7, 'Rens', 'Geubbels', 'rens@gmail.com', '06 49724952'),
           (8, 'Arne', 'Martens', 'arne@gmail.com', '456787834'),
           (9, 'Erik', 'Griffioen', 'erik@gmail.com', '06 99834756'),
           (10, 'Henning', 'van Laarhoven', 'henning@gmail.com', '06 74673392'),
           (11, 'Suzanne', 'van der Leeden', 'suzanne@soundrental.nl', '06 15051965'),
           (12, 'Fien', 'Verster', 'fien@soundrental.nl', '06 12281996'),
           (13, 'Elise', 'Verheyden', 'elise@soundrental.nl', '06 12281996');

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
    values (1, 2027-07-23, 'Mezz'),
           (2, 2027-06-29, 'cafe de Kerel'),
           (3, 2027-08-02, 'Electron');

-- Insert into act
    insert into acts (id, name, phone, email)
    values (1, 'Rita Lynn', '06 45188510', 'info@ritalynn.zu'),
           (2, 'R.B. Pine', '06 63547483', 'info@rbpine.nl'),
           (3, 'Fifi Hengsten', '06 53889223', 'info@fifihengsten.nl');

-- Insert into equipment
    insert into equipment (id, brand, model)
    values (1, 'Shure', 'SM58'),
           (2, 'Shure', 'SM58'),
           (3, 'Shure', 'SM58'),
           (4, 'Shure', 'SM58'),
           (5, 'Shure', 'SM57'),
           (6, 'Shure', 'SM57'),
           (7, 'Shure', 'Beta 52A'),
           (8, 'Sennheiser', 'e904'),
           (9, 'Sennheiser', 'e904'),
           (10, 'Sennheiser', 'e904'),
           (11, 'Sennheiser', 'e904'),
           (12, 'Sennheiser', 'e906'),
           (13, 'Sennheiser', 'e906'),
           (14, 'AKG', 'C414 XLS'),
           (15, 'AKG', 'C414 XLS'),
           (16, 'AKG', 'C411 PP'),
           (17, 'AKG', 'C411 PP'),
           (18, 'Mackie', 'ProFX22v3'),
           (19, 'Mackie', 'Onyx24'),
           (20, 'Mackie', '1604 VLZ4');


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
           (14, true),
           (15, true),
           (16, true),
           (17, true);

-- Insert into microphone_polar_patterns
    insert into microphone_polar_patterns (id, polar_pattern)
    values (1, CARDIOID),
           (2, CARDIOID),
           (3, CARDIOID),
           (4, CARDIOID),
           (5, CARDIOID),
           (6, CARDIOID),
           (7, CARDIOID),
           (8, CARDIOID),
           (9, CARDIOID),
           (10, CARDIOID),
           (11, CARDIOID),
           (12, CARDIOID),
           (13, CARDIOID),
           (14, CARDIOID),
           (14, OMNIDIRECTIONAL),
           (14, FIGURE_8),
           (15, CARDIOID),
           (15, OMNIDIRECTIONAL),
           (15, FIGURE_8),
           (16, FIGURE_8),
           (17, FIGURE_8);


-- Insert into mixers
    insert into mixers (id)
    values (18),
           (19),
           (20);


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
    values (1, 11, AUTO),
           (2, 12, VRACHTWAGEN),
           (3, 13, KLEINE_BUS);

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
           (23, 16, 'lead vocals harp Erik'),
           (24, 17, 'blues harp Erik'),
           (25, 18, 'electric bass Henning');


-- Insert into channel (needs mixer + optional source)
    insert into channels (id, mixer_id, number, source_id)
    values (1,18,1,11),
           (2,18,2,12),
           (3,18,3,13),
           (4,18,4,14),
           (5,18,5,15),
           (6,18,6,16),
           (7,18,7,17),
           (8,18,8,20),
           (9,18,9,18),
           (10,18,10,19),
           (11,18,11, null),
           (12,18,12, null),
           (13,18,13, null),
           (14,18,14, null),
           (15,18,15, null),
           (16,18,16, null),
           (17,18,17, null),
           (18,19,1,11),
           (19,19,2,12),
           (20,19,3,13),
           (21,19,4,14),
           (22,19,5,15),
           (23,19,6,16),
           (24,19,7,17),
           (25,19,8,8),
           (26,19,9,1),
           (27,19,10,4),
           (28,19,11,7),
           (29,19,12,2),
           (30,19,13,3),
           (31,19,14,9),
           (32,19,15,10),
           (33,19,16,5),
           (34,19,17,6),
           (35,19,18, null),
           (36,20,1,11),
           (37,20,2,12),
           (38,20,3,13),
           (39,20,4,14),
           (40,20,5,15),
           (41,20,6,16),
           (42,20,7,17),
           (43,20,8,25),
           (44,20,9,23),
           (45,20,10,21),
           (46,20,11,24),
           (47,20,12,22),
           (48,20,13, null),
           (49,20,14, null),
           (50,20,15, null),
           (51,20,16, null);

-- Insert into performance (needs event + act)

-- Insert into performer-act (needs performer-profile + act)

-- Insert into event-assignment (event + person)

-- Insert into equipment-event-assignment (needs event + equipment)