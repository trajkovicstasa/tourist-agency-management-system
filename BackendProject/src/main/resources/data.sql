insert into turisticka_agencija(id, naziv, adresa, kontakt)
values(nextval('agencija_seq'), 'Test naziv', 'Test adresa', 'Test kontakt');
insert into turisticka_agencija(id, naziv, adresa, kontakt)
values(nextval('agencija_seq'), 'Egida', 'Gogoljeva 9', '064665899');
insert into turisticka_agencija(id, naziv, adresa, kontakt)
values(nextval('agencija_seq'), 'Travels', 'Tolstojeva 10', '0648596231');
insert into turisticka_agencija(id, naziv, adresa, kontakt)
values(nextval('agencija_seq'), 'GlobalTravel', 'Puskinova 11', '0645823654');

insert into destinacija(id, mesto, drzava, opis)
values(nextval('destinacija_seq'), 'Tivat', 'Crna Gora', 'grad za letovanje');
insert into destinacija(id, mesto, drzava, opis)
values(nextval('destinacija_seq'), 'Milano', 'Italija', 'grad mode');
insert into destinacija(id, mesto, drzava, opis)
values(nextval('destinacija_seq'), 'Lisabon', 'Portugal', 'grad kulture na moru');
insert into destinacija(id, mesto, drzava, opis)
values(nextval('destinacija_seq'), 'London', 'Velika Britanija', 'grad istorije britanske kraljevske porodice');

insert into hotel(id, naziv, broj_zvezdica, opis, destinacija)
values(nextval('hotel_seq'), 'Hotel Palma', 4, 'Hotel blizu mora', 1);
insert into hotel(id, naziv, broj_zvezdica, opis, destinacija)
values(nextval('hotel_seq'), 'Grand Milano', 5, 'Luksuzni hotel u centru', 2);
insert into hotel(id, naziv, broj_zvezdica, opis, destinacija)
values(nextval('hotel_seq'), 'Lisbon View', 3, 'Pogled na okean', 3);
insert into hotel(id, naziv, broj_zvezdica, opis, destinacija)
values(nextval('hotel_seq'), 'Royal London', 5, 'Hotel u srcu Londona', 4);

insert into aranzman(id, ukupna_cena, placeno, datum_realizacije, hotel, agencija)
values (nextval('aranzman_seq'), 500.00, true, '2025-07-10', 1, 1);
insert into aranzman(id, ukupna_cena, placeno, datum_realizacije, hotel, agencija)
values (nextval('aranzman_seq'), 1200.00, false, '2025-08-15', 2, 2);
insert into aranzman(id, ukupna_cena, placeno, datum_realizacije, hotel, agencija)
values (nextval('aranzman_seq'), 800.00, true, '2025-06-20', 3, 3);
insert into aranzman(id, ukupna_cena, placeno, datum_realizacije, hotel, agencija)
values (nextval('aranzman_seq'), 1500.00, false, '2025-09-05', 4, 4);


