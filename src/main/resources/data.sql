-- Insert into person

-- Insert into instrument

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