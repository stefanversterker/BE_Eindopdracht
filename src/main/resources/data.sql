-- Insert into person
    insert into persons (id, first_name, last_name, email, phone)
    values (1, 'Bart', 'Brosens', 'bart@ritalynn.zu', '06 18031976'),
           (2, 'Mark', 'Schrauwen', 'mark@ritalynn.zu', '06 23061985'),
           (3, 'Barry', 'Stuijts', 'barry@ritalynn.zu', '06 19081979'),
           (4, 'Olaf', 'van den Berg', 'olaf@ritalynn.zu', '06 15111984'),
           (5, 'Stefan', 'Verster', 'stefan@ritalynn.zu' ,'06 24041982'),
           (6, 'Kees', 'Paneermeel', 'kees@paneermeel.nl', '06 15051965'),
           (7, 'Rick', 'Paneermeel', 'rick@paneermeel.nl', '06 12281996');

-- Insert into instrument
    insert into instruments (id, name)
    values (1, 'lead vocals'),
           (2, 'backing vocals'),
           (3, 'electric bass guitar'),
           (4, 'acoustic guitar'),
           (5, 'electric guitar'),
           (6, 'blues harp'),
           (7, 'drums');

-- Insert into event
    insert into events (id, date, venue)
    values (1, 2027-07-23, 'Breda Barst'),
           (2, 2027-06-29, 'Van Gogh Live'),
           (3, 2027-08-02, 'Trailerfest');




-- Insert into act

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