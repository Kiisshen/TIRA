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

## 04-TASK

## 05-TASK

## 06-TASK

## 07-TASK

## 08-TASK

## 09-TASK
