-- Insert into person
    insert into persons (id, first_name, last_name, email, phone)
    values (1, 'Bart', 'Brosens', 'bart@gmail.com', '06 18031976'),
           (2, 'Mark', 'Schrauwen', 'mark@gmail.com', '06 23061985'),
           (3, 'Barry', 'Stuijts', 'barry@gmail.com', '06 19081979'),
           (4, 'Olaf', 'van den Berg', 'olaf@gmail.com', '06 15111984'),
           (5, 'Stefan', 'Verster', 'stefan@gmail.com' ,'06 24041982'),
           (6, 'Abi', 'Borcic', 'abi@gmail.com', '06 63547483'),
           (7, 'Rens', 'Geubbels', 'rens@gmail.com', '06 49724952'),
           (8, 'Leon', 'van den Langenberg', 'leon@gmail.com', '456787834'),
           (9, 'Wies', 'Arts', 'wies@gmail.com', '06 99834756'),
           (10, 'Rop', 'van de Laar', 'rop@gmail.com', '06 74673392'),
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
    values (1, 2027-07-23, 'Breda Barst'),
           (2, 2027-06-29, 'Van Gogh Live'),
           (3, 2027-08-02, 'Trailerfest');

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
           (18, 'Allen & Heath', 'CQ18T'),
           (19, 'Allen & Heath', 'QU-5D'),
           (20, 'Allen & Heath', 'QU-7D');


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
    insert into microphone_polar_patterns (microphone_id, polar_pattern)
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

-- Insert into source (needs performer-instrument)

-- Insert into channel (needs mixer + optional source)

-- Insert into performance (needs event + act)

-- Insert into performer-act (needs performer-profile + act)

-- Insert into event-assignment (event + person)

-- Insert into equipment-event-assignment (needs event + equipment)