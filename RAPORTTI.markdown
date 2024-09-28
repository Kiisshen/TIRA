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

## 03-TASK

## 04-TASK

## 05-TASK

## 06-TASK

## 07-TASK

## 08-TASK

## 09-TASK
