insert into persons (id, email, first_name, last_name, phone)
values (1, 'person1@test.nl','person-1-fn','person-1-ln','0612345678');

insert into persons (id, email, first_name, last_name, phone)
values (2, 'person2@test.nl','person-2-fn','person-2-ln','0687654321');

insert into employee_profiles (id, person_id, drivers_license)
values (1,1,'AUTO');

insert into instruments (id, name)
values (1, 'instrument1');

insert into performer_profiles (id, person_id)
values (1, 2);

insert into performer_instruments (id, instrument_id, performer_profile_id)
values (1,1,1);