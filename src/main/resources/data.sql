-- Insert into person
    insert into persons (id, first_name, last_name, email, phone)
    values (1, 'Bart', 'Brosens', 'bart@ritalynn.zu', '06 18031976'),
           (2, 'Mark', 'Schrauwen', 'mark@ritalynn.zu', '06 23061985'),
           (3, 'Barry', 'Stuijts', 'barry@ritalynn.zu', '06 19081979'),
           (4, 'Olaf', 'van den Berg', 'olaf@ritalynn.zu', '06 15111984'),
           (5, 'Stefan', 'Verster', 'stefan@ritalynn.zu' ,'06 24041982'),
           (6, 'Kees', 'Paneermeel', 'kees@paneermeel.nl', '06 15051965'),
           (7, 'Rick', 'Paneermeel', 'rick@paneermeel.nl', '06 12281996'),
           (8, 'Abi', 'Borcic', 'abi@rbpine.nl', '06 63547483'),
           (9, 'Rens', 'Geubbels', 'rens@gmail.com', '06 49724952'),
           (10, 'Leon', 'van den Langenberg', 'leon@fifihengsten.nl', '456787834'),
           (11, 'Wies', 'Arts', 'wies@fifihengsten.nl', '06 99834756'),
           (12, 'Rop', 'van de Laar', 'rop@fifihengsten.nl', '06 74673392');

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
           (3, 'Fifi Hengsten', '06 53889223', 'info@fifihengsten.nl')


-- Insert into equipment

-- Insert into performerProfile (needs person)

-- Insert into employeeProfile (needs person)

-- Insert into performer-instrument (needs performer-profile + instrument)

-- Insert into source (needs performer-instrument)

-- Insert into channel (needs mixer + optional source)

-- Insert into performance (needs event + act)

-- Insert into performer-act (needs performer-profile + act)

-- Insert into event-assignment (event + person)

-- Insert into equipment-event-assignment (needs event + equipment)