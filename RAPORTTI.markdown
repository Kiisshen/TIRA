# Raportit tehtävistä

Kirjaa tähän tiedostoon **jokaiseen** tehtävään liittyvät omat raporttisi ja analyysisi. Muista että raportti on myös kurssilla **arvosteltava tehtävä**.

Voit sisällyttää raporttiin tekstimuotoisia taulukoita (tasaukset välilyönnein):

```
n     Fill     Search   Total
500   7        700      707
1000  9        288      297
```

Ja näihin liittyviä kuvatiedostoja:

![Esimerkkikuva](report-sample-image.png)

Nämä näkyvät sitten VS Coden Preview -näkymässä (tai oman repositorysi webbisivulla) oikein muotoiltuna. Käytä tässä dokumentissa olevia muotoiluja esimerkkinä kun kirjoitat raporttiasi.

Huomaa että jos laitat kuvatiedostot vaikka omaan alihakemistoonsa, Markdown -muotoilussa on oltava suhteellinen polku tiedostoon, esimerkiksi `images/report-sample-image.png`. **Älä** käytä absoluuttisia polkuja `C:\Users\tippaleipa\kurssit\TIRA\kuva.png`, koska nämä eivät tietenkään toimi opettajan koneella. Ei kannata laittaa linkkiä etärepoosikaan, vaan nimenomaan paikalliseen tiedostoon.

Voit myös sisällyttää _lyhyitä_ koodinpätkiä vaikkapa Java -formaatilla:

```Java
	@Override
	public int hashCode() {
		// Oma nerokas hajautufunktioni!
	}
```

Tarvittaessa käytä myös paremmin muotoiltuja taulukoita:

| n    | Fill | Search |  Total |
| ---- | ---- | ------ | ------ |
| 500  |  7   | 700    | 707    |
| 1000 | 9    | 288    | 297    |

Alaluvut jokaisen tehtävän raportille löydät alta.

## 01-TASK

Opin tehtävää tehdessä insertionsort ja reverse algoritmien toimintaa ja toteutusta.
Opin myös käyttämään Comparable ja Comparator interfaceja, ja ymmärrän nyt niiden toimintaa ja rakennetta paremmin.
Vaikeaa olikin juuri näiden rakenteiden ymmärtäminen, ja helppoa oli algoritmien toteutus materiaalin pohjalta.

Insertion sort algoritmin aikakompleksisuusluokka n^2, sillä pahimmassa tapauksessa lista on valmiiksi käänteisessä järjestetyssä järjestyksessä, jolloin jokainen alkio joudutaan siirtämään n kertaa. Parhaassa tapauksessa taas riittää juosta listan läpi ja tarkistaa sen järjestys, jolloin luokka olisi n.

Reversen aikakompleksisuusluokka taas on n/2, sillä operaatioita joudutaan aina tekemään vain puolet listan alkioiden määrästä.

Näiden mietiskelyiden pohjalta, sanoisin että taulukon ollessa järjestetty nousevaan järjestykseen, laskevaan järjestykseen siirtminen on 2 kertaa nopeampaa reverse algoritmilla (n/2), kuin järjestämällä lista uudelleen (n^2)

Kaiken kaikkiaan tämä oli laaja, hauska ja opetttavainen tehtävä, joskin hieman työläs.

## 02-TASK

![alt text](image.png)

add:n ja hakualgoritmin molempien aikakompleksisuus näyttäisi olevan O(n), sillä add:ssä joudutaan käymään taulukko läpi duplikaattien varalta, ja hakualgoritmissa huonoimmassa tapauksessa joudutaan käymään läpi koko taulukko, ennenkuin löydetään haluttu elementti.

add:n käyrä kuitenkin hieman eksponentiaalisemmalta verrattuna lineaarisen näköiseen hakualgoritmin käyrään.
Luulen että add:iä alkaa ajan myötä hidastamaan reallocate() funktio, koska aina vain suurempaa taulukkoa joudutaan kopioimaan uuteen taulukkoon. n:n kasvaessa tämä tekee add:n käyrästä hieman eksponentiaalisen.

Tätä tehtävää tehdessäni opin lisää Comparator ja Comparable rajapinnoista, ja ongelmanratkaisusta. Tehtävässä ei niinkään ollut mitään vaikeaa, mutta monia paikkoja mihin voi kompastua ja jäädä pitkäksi aikaa jumiin. Tehtävä oli hauska ja opettavainen.

Lajitteluajat näyttävät muuttuvan aika paljon operaation perusteella. Välillä lajittelu on välitön ja välillä taas ottaa useita sekunteja. Lineaarinenhaku näyttää toimivan vielä aika nopeasti näin pienelle määrälle dataa.

Lajitteluaikojen vaihtelu johtuu siitä, että reverse funktion aikakompleksisuus on vakio, kun taas sortin kompleksisuus nousee n:n kasvaessa. Joissain tilainteissa, jos taulukko on jo valmiiksi järjestetty, se voidaan vain kääntää, eli sortti on nopeampi. Esim. jos FullName Ascending, faihto FullName Descendingiin on nopea koska se voidaan suorittaa reversellä. Jos taas vaihdetaan CodeName Ascendingiin, joudutaan sort tekemään uudelleen, ja vaihto kestää useita sekunteja.

Juuri tästä syystä käyttäisinkin myös yleisesti ajateltuna reversealgoritmia jos minulla olisi valmiiksi järjestetty aineisto joka tulisi kääntää.

Toteutettua algoritmia kutsutaan lienee lineaariseksi siksi, että se etenee lineaarisesti kunnes pääsee maaliin. Sen aikakompleksisuus on myös lineaarinen, eli riippuu datan suuruudesta.

Niiden aikakompleksisuus on O(N).

```
n       F (ms)  S (us)  Total (ms)
1000    11      1313    12
2000    13      767     13
3000    19      1196    20
4000    29      429     29
5000    42      562     42
6000    64      137     64
7000    93      155     93
8000    124     192     124
9000    148     274     148
10000   184     239     184
11000   219     266     219
12000   239     260     239
13000   289     308     289
14000   371     364     371
15000   393     374     393
16000   443     363     443
17000   502     412     502
18000   573     386     573
19000   632     406     632
20000   695     433     695
21000   783     462     783
22000   844     473     844
23000   946     490     946
24000   1013    518     1013
25000   1100    539     1100
26000   1182    561     1182
27000   1268    587     1268
28000   1308    612     1308
29000   1634    722     1634
30000   1656    688     1656
31000   1688    765     1688
32000   1852    700     1852
33000   1938    720     1938
34000   2005    748     2005
35000   2151    772     2151
36000   2269    912     2269
37000   2233    642     2233
38000   2708    941     2708
39000   2669    963     2669
40000   2805    886     2805
41000   3083    917     3083
42000   3511    983     3511
43000   3388    1001    3389
44000   3612    1019    3613
45000   3662    932     3662
46000   4428    1873    4429
47000   4121    1172    4122
48000   4371    1285    4372
49000   4976    1099    4977
50000   4501    1148    4502
```

## 03-TASK

Översti:

PhoneBookArray: Search took 43 ms
PhoneBookArray: Fast search took 0 ms

Muutamia muita hakuja eripuolilta listaa:

PhoneBookArray: Search took 19 ms
PhoneBookArray: Fast search took 0 ms

PhoneBookArray: Search took 28 ms
PhoneBookArray: Fast search took 0 ms

PhoneBookArray: Search took 2 ms
PhoneBookArray: Fast search took 0 ms

PhoneBookArray: Search took 3 ms
PhoneBookArray: Fast search took 0 ms

Nopean haun keskimääräinen aika on 0ms, kun taas puolitushaun aika näyttää olevan hieman matalempi listan alkupäässä.

Nopea haku lienee nopeampi koska sillä vertaillaan suoraan merkkijonoja, kun taas epätarkassa haussa vertaillaan kokonaisia olioita, eli voidaan hakea minkä vain nimen perusteella.

Aineiston alusta ja lopusta hakeminen näyttää vaikuttavan tavalliseen puolitushakuun mutta nopeaan hakuun se ei ainakaan vielä 50000 koodarilla näytä vaikuttavan, vaan haku aika on aina ~0ms.

Pienillä aineoistoilla hakuajat ovat molemmissa tapauksissa listan molemmissa päissä lähellä nollaa, eli niissä tapauksissa ei lienee väliä kumpaa käytetään, mitä nyt tarkkaan hakuun hakusanan kirjoittaminen on tarkempaa ja vie enemmän aikaa.

```Data:
n       Fill    Sort    binSearch       Total
1000    3       5       331     339
2000    7       10      21      38
3000    15      25      36      76
4000    28      40      24      92
5000    41      68      30      139
6000    60      104     57      221
7000    84      141     29      254
8000    110     177     73      360
9000    143     231     104     478
10000   177     287     31      495
11000   210     332     27      569
12000   248     388     28      664
13000   292     495     55      842
14000   344     567     51      962
15000   428     632     25      1085
16000   451     745     50      1246
```

Graafit:
![alt text](image-1.png)

Puolitushaun aikakompleksisuus luokka on O(log(2) N), mikä johtunee juurikin algoritmin luonteesta puolittaa aineistoa niin kauan että löytää etsityn alkion.

Tämä on huomattavasti nopeampi lineaariseen hakuun verrattuna, jossa joudutaan pahimmillaan käymään läpi koko taulukko, ennenkuin löydetään haluttu alkio. (O(N))

Tehtävää tehdessäni pääsin palauttamaan mieleen puolitushaun ja comparatorin toimintaa. Iteratiivisen haun toteutus oli helppoa, sillä sen pseudo koodi oli hyvin ymmärrettävää wikipediassa ja materiaali oli helposti ymmärrettävää. Vaikeaa oli saada vertailuoperaattorit ja indeksit oikein algoritmeihin, niin että ne toimivat halutulla tavalla, vaikka ei sekään silti paljoa päänvaivaa tuottanut.

## 04-TASK

Pinorakenteen toteutus oli helppo hetikun ymmärsi miten sen taustalla toimiva interface toimii, ja sen totetuksessa ei ollut mitään ongelmaa. Samoin ParenthesisChecker oli helppo tehdä, mutta päädyin kokeilemaan siihen ainakin 4:jää eri toteutusta ja debuggamaan turhaan 3 tuntia koska en ollut huomannut että tehtävän testit olivat väärin forkatussa repossa.

Tulipahan siis niinsanottua toistokoulutusta tämän toteutuksen tekemiseen, ja nyt ainakin ymmärrän täysin sen toiminnan sen myötä että olen toteuttanut sen neljällä eri tavalla?

Tehtävää oli enimmäkseen hauska tehdä ja se oli hyvin opettavainen, vaikka pinorakenne on joskus käyty ammattikoulussa läpi niin sen kertaaminen ei ollut kyllä pahitteeksi.

Analysoidaan pinototeutustani aikakompleksisuusvaatimusten pohjalta:

- `capacity()`: O(1).
  Palauttaa muuttujan, ei kutsu toisia funktioita = toteutuu.
- `push()`: O(1) paitsi kun joudutaan reallokoimaan: O(n).
  Palauttaa muuttujan = toteutuu; kun reallokoidaan käydään n pituinen for loop läpi taulukkoa kopioitaessa = toteutuu.
- `pop()`: O(1).
  Palauttaa muuttujan, ei kutsu toisia funktioita = toteutuu.
- `peek()`: O(1).
  Palauttaa muuttujan, ei kutsu toisia funktioita = toteutuu.
- `size():` O(1).
  Palauttaa muuttujan, ei kutsu toisia funktioita = toteutuu.
- `isEmpty():` O(1).
  Palauttaa muuttujan, ei kutsu toisia funktioita = toteutuu.
- `clear()`: O(1).
  Raporttia tehdessäni huomasinkin clear funktioni olevan luokkaa O(N), sillä korvasin siinä for loopilla jokaisen alkion nullilla. Nyt aikakompleksisuus myös O(1):

```java
	array = new Object[DEFAULT_STACK_SIZE];
	tail = 0;
```

- `toString()`: O(n).
  Käydään n pituinen for loop läpi taulukkoa tulostaessa = toteutuu.

## 05-TASK

Linkitetyt listat olivat jo tuttuja C++ Intermediate Programming kurssilta, mutta kertaus on opintojen äiti. Linkitetyn listan tekeminen tuntui jopa helpommalta, ainakaan reallokointia ei tarvitse pahemmin miettiä.

Jonkun verran tämä taas aiheutti päänvaivaa, lähinnä indeksit ja jakojäännökset, mutta vaikka tehtävä on vähän aikaa vievä niin se ei ollut ylitsepääsemättömän vaikea.

Tehtävästä teki hauskan se että lopuksi nähtiin miten rakennetta käytetään oikeasti, ja sen integrointi TIRA Codersiin ei vaatinut itseltä mitään ylimääräistä tiedostojen välillä loikkimista.

Linkitetty lista lienee parempi jono tietorakenteelle, koska siinä ei käytetä listan keskellä olevia elementtejä. Kuitenkin jos puhuttaisiin normaaleista listoista, niin esim array[idx] aikakompleksisuus lienee paljon linkitettyä listaa pienempi, sillä linkitetty lista jouduttaisiin pahimmassa tapauksessa juoksemaan kokonaan läpi jolloin päädyttäisiin O(N).

Linkitetty lista on myös parempi jos listan keskelle tarvitsee lisätä tietoa, olettaen tietty että tiedetään alkio jonka perään tieto lisätään. Linkitetyssä listassa voidaan vain manipuloida pointtereita, kun taas taulukossa joudutaan siirtämään muita elementtejä oikealle, mikä hidastaa operaatiota.

Analysoidaan jonototeutuksia aikakompleksisuuden näkökulmasta:

- `capacity()`: O(1).
  Palauttaa vain capacity = toteutuu.
- `enqueue()`: O(1) paitsi kun/jos joudutaan reallokoimaan taulukkoratkaisussa: O(n).
  Pelkkää osoitin manipulaatiota = Toteutuu.
  Kun reallokoidaan, O(n). Linkitetyssä listassa O(1).
- `dequeue()`: O(1).
  Jälleen pelkkää osoitin manipulaatiota = Toteutuu.
- `element()`: O(1).
  Palauttaa vain array[head] = Toteutuu.
- `size():` O(1).
  Palauttaa vain size = Toteutuu.
- `isEmpty():` O(1).
  Palauttaa vain boolean(size == 0) = Toteutuu.
- `clear()`: O(1).
  Tyhjentää taulukon vaihtamalla sen uuteen tyhjään taulukkoon = Toteutuu.
- `toString()`: O(n).
  Käy n pituisen taulukon (array) läpi, ja tulostaa sen alkiot = Toteutuu.

## 06-TASK

InsertionSort:
![alt text](image-2.png)
QuickSort:
![alt text](image-3.png)

## 07-TASK

## 08-TASK

## 09-TASK
