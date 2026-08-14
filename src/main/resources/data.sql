-- Insert into person
    insert into person (first_name, last_name, email, phone)
    values ('Bart', 'Brosens', 'bart@ritalynn.zu', '06 18031976'),
           ('Mark', 'Schrauwen', 'mark@ritalynn.zu', '06 23061985'),
           ('Barry', 'Stuijts', 'barry@ritalynn.zu', '06 19081979'),
           ('Olaf', 'van den Berg', 'olaf@ritalynn.zu', '06 15111984'),
           ('Stefan', 'Verster', 'stefan@ritalynn.zu' ,'06 24041982'),
           ('Kees', 'Paneermeel', 'kees@paneermeel.nl', '06 15051965');

-- Insert into instrument
    insert into instrument (name)
    values ('lead vocals'),
           ('backing vocals'),
           ('electric bass guitar'),
           ('acoustic guitar'),
           ('electric guitar'),
           ('blues harp'),
           ('drums');

-- Insert into event

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