Zadatak se sastoji iz osnovnih minimalnih zahteva i iz bonus dela.
Prilikom razvoja aplikacije potrebno je svaku funkcionalnost pojedinačno komitovati na git repozitorijum sa opisnom
porukom.
Kada završite zadatak, možete poslati link od git repozitorijuma sa zadatkom na sledeće mejl adrese:
nemanja.gruzanic@heliant.rs i filip.maljkovic@heliant.rs.
Zahtevi:
1. Inicijalizovati java aplikaciju koristeći Spring Boot.
2. Korititi MySQL za definsianje modela podataka za entitete Korisnik, Statistika,
Formular,Polje,FormularPopunjen i PoljePopunjeno entiteta.
3. Omogućiti autentifikaciju korisnika korišćenjem JWT tokena.
4. Implementirati CRUD operacije za sve entitete(WS za kreiranje popunjenog formulara podrazumeva
snimanje popunjenog formulara zajedno sa popunjenim poljima). Koristiti RESTful API za endpointe.
Nije potrebno implementirati CRUD operacije za korisnike i statistiku. Dovoljno je uneti par korisnika
koristeći SQL skriptu. Za enkripciju lozinke koristiti https://bcrypt-generator.com/
5. Implementirati validaciju podataka za obavezna polja.
6. Kreirati task koji će svakog dana u ponoć prebrojati koliko je formulara popunjeno u toku
prethodnog dana. Rezultate sačuvati u tabelu statistika.

![image](https://github.com/MarkoKostic/HeliantTask/assets/16134880/c73cf4fc-b386-4850-9b2b-d3644521f0f8)



Dodatni zadaci (opciono):
Ukoliko vreme dozvoli, moguće je kreirati i sledeće funkcionalnosti:
- Dodavanje kolona id_korisnik_kreirao, id_korisnik_poslednji_azurirao. Obezbediti
automatsko postavljanje ovih podataka. (Nije potrebno za tabelu statistika i korisnik)
- Korišćenje liquibase-a (ukoliko se ne odradi ovaj deo, potrebno je sve SQL skripte poslati uz zadatak)
- Dodajte podršku za autorizaciju korisnika. Uvesti role ADMIN i RADNIK.
Samo admin može manipulisati nad formularom i njegovim poljima.
- Uvesti paginaciju pri dohvatanju liste svih formulara
