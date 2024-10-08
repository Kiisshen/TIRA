# Tehtävä 4

Älä tee tätä tehtävää ennenkuin olet tehnyt [tehtävän 3](03-TASK.markdown).

Siirrymme tehtävässä lajittelu- ja hakualgoritmeista *tietorakenteisiin*. Ensimmäisenä tutustumme perustietorakenteisiin kuuluvan pinotietorakenteen (*stack*).

> Tässä tehtävässä kirjoitat noin 155 riviä koodia, riippuen koodaustyylistä.

Toteutat tässä harjoituksessa **pinotietorakenteen** ja käytät sitä todelliseen käytännölliseen tarpeeseen.

TIRA Coders käsittelee JSON -tiedostoja joissa koodareiden tiedot ovat. Joskus joku tiedosto saataa olla korruptoitunut tai syntaktisesti väärin:

- JSON -tietorakenteista puuttuu sulkuja, joko avaavia tai sulkevia,
- jompia kumpia (avaavia tai sulkevia) sulkuja on liikaa,
- avaavaa sulkua vastaava sulkeva sulku on väärä, esim. JSON -taulukko alkaa sululla `[` mutta se suljetaankin sululla `}` joka on *JSON -olion* sulkeva sulku.

Jos et ole ennen nähnyt JSON -tiedostoja, katso miltä coders -tiedostot näyttävät.

Samat asiat pätevät myös Java -koodiin -- niissäkin sulut on oltava kohdallaan, niitä on oltava oikea määrä oikeissa paikoissa. Koodieditorisihan (kun käytät hyvää sellaista) osaa näyttää jos sulut eivät ole kohdallaan. Eli editorisi koodarit ovat tehneet sinne myös ominaisuudet jotka analysoivat koodia, esimerkiksi sulkujen oikeellisuutta, ja osaavat ilmoittaa virheistä. Samaa tietysti osaavat tehdä myös kääntäjät. Ja tämän tehtävän koodaamisen jälkeen sinäkin osaat, ainakin yksinkertaisella tavalla!

Pinotietorakenteella voidaan yksinkertaisella tavalla tarkistaa ovatko sulut tekstissä rakenteisen kielen mukaisia vaiko eivät.

## Lähteitä

* Kurssin luentokalvot.
* Liveluento (tallenne) ja sen vinkit ja esimerkit.
* Kurssikirja: Introduction to Algorithms, 4th ed (Comer et al) ss 254-255.
* Pinon rajapintaluokka `oy.interact.tira.student.StackInterface` ja sen dokumentaatio.

## Tavoite

Pinotietorakenne on siis kätevä tapa tarkistaa onko tällainen rakenteinen teksti syntaktisesti oikein näiden sulkujen kannalta.

Siksi toteutat ensin geneerisen pinotietorakenteen, ja sen jälkeen käytät sitä testaamaan tarkistimella (`ParenthesisChecker` -luokka) JSON ja Java -tiedostojen sulkujen oikeellisuutta.

> HUOM: tarkistin toimii vain yksinkertaisten Java -tiedostojen kanssa. Sen ei tarvitse ottaa huomioon koodin kommentteja eikä siis toimi kaikkien Java -tiedostojen kanssa. Testit käyttävät testaamiseen sellaisia yksinkertaisia Java -tiedostoja joiden kanssa tarkistimen tulisi toimia samoilla säännöillä kuin JSON -tiedostojenkin kanssa.

Pinon sisäisenä tietorakenteena käytetään **taulukkoa**. Toteutuksen **on täytettävä** seuraavat aikakompleksisuusluokkavaatimukset:

  * `capacity()`: O(1).
  * `push()`: O(1) paitsi kun joudutaan reallokoimaan: O(n).
  * `pop()`: O(1).
  * `peek()`: O(1).
  * `size():` O(1).
  * `isEmpty():` O(1).
  * `clear()`: O(1).
  * `toString()`: O(n).

Metodin `clear()` toteutuksessa pinon kapasiteetiksi on tultava oletuskapasiteetti! Huomaa myös että aikakompleksisuusvaatimus tässä *todellakin* on O(1), eikä O(n).

Pinon ei tule käyttää enempää muistia kuin mitä sen maksimikapasiteetti milloinkin on. Esimerkiksi kahden (tai useamman) taulukon pitäminen *pysyvästi* muistissa (pinon jäsenmuuttujana) on *virhe*.

Pinoa toteuttaessasi, **varmista** ettet laita luokan jäsenmuuttujiksi sellaisia tietoelementtejä joita ei tarvita koko pino-olion elinajan. Jos tarvitset jotain muuttujaa ja sen arvoa vain yhdessä metodissa, muuttuja kuuluu metodin paikalliseksi muuttujaksi. Turhat jäsenmuuttujat kuluttavat turhaa muistia. Ne voivat myös aiheuttaa bugeja.

**Huomaa** että `toString()` -metodi tässä ja myöhemmissäkin harjoituksissa toteutetaan käyttäen Javan `StringBuilder` -luokkaa, **ei** `String` -oliota muokkaillen. Suuria määriä elementtejä käsitellessä `String`:n avulla merkkijonon muodostaminen on **satoja tai tuhansia kertoja hitaampaa** kuin `StringBuilder`in käyttäminen. Jos `toString()` käyttää `String`iä, siitä saa **miinuspisteen**.

> `String` -olion jatkuva muokkaaminen on hidasta, sillä `String` on niinsanottu [immutable](https://en.wikipedia.org/wiki/Immutable_object) olio. Vaikka merkkijonoon voi ohjelmallisesti lisätä merkkejä ja muokkailla niitä (`String`:n operaatiot `+`, `+=`, ja metodit `.append()`, jne), jokainen kerta syntyy *uusi* merkkijono-olio johon muutokset tallennetaan. Tämä tarkoittaa jatkuvaa muistin allokointia ja merkkien kopiointia paikasta toiseen. Siksi merkkijonojen rakentelua ja muuttamista pitäisi tehdä vain jos se on hyvin yksinkertaista ja tilanteissa joissa tätä ei toisteta satoja tai tuhansia kertoja. Koska pinotietorakenteessa voi olla satoja tai tuhansia (tai enemmänkin) olioita, niistä merkkijonon muodostaminen `String`:llä on [hidasta](https://stackoverflow.com/a/1253519/10557366). **Käytä** siis merkkijonojen "kasaamiseen" aina `StringBuilder`:ia joka on huomattavasti nopeampi.

**Huomaa** myös, että kun tietoelementti poistetaan pinosta kutsumalla `pop()` -metodia, taulukossa oleva viittaus olioon on myös poistettava. Tämä tapahtuu sijoittamalla kyseiseen taulukon indeksiin `null`. Jos tätä ei tehdä, taulukkoon jää viittaus kyseiseen olioon, eikä Javan roskien keruu voi sitten vapauttaa oliota muistista, kun `pop()`:n palauttamaa viittaustakaan ei enää käytetä. Tällöin tapahtuu muistivuoto (memory leak) joka on **ohjelmointivirhe**.

Kirjoita lopuksi **raporttiin** (`RAPORTTI.markdown` löytyy valmiiksi projektin juurihakemistosta) analyysisi tehtävästä, annettujen ohjeiden mukaisesti. Huomaa että myös raportti on arvosteltava tehtävä.


## Askel 1 - Ohjeet

**Toteuta** rajapinta `oy.interact.tira.util.StackInterface` omaan luokkaasi `oy.interact.tira.student.StackImplementation`. Älä muuta millään tavalla annettua rajapintaluokkaa.

Esimerkiksi, kun rajapintaluokassa on metodi...:

```Java
   public E peek() throws IllegalStateException;
```
...toteutusluokassasi on oltava vastaava *täsmälleen* samanlainen metodi:

```Java
   @Override
   public E peek() throws IllegalStateException {
		// Toteutus tänne.
	}
```

> Kun käytät jo aiemmin selitettyä `@Override` -annotaatiota, kääntäjä antaa virheen jos toteutus ei vastaa rajapinnan esittelyä. Jos et käytä annotaatiota, virhe jää huomaamatta.

**Lue huolellisesti** rajapintaluokan dokumentaatio, joka kertoo miten rajapinnan tulee käyttäytyä. Toteuta pino näiden ohjeiden mukaisesti.

Huomaa, että luokan metodit toimivat yhdessä ja yhteistyössä, käsitellen pinon sisäistä taulukkoa (tästä alla lisää) jota siellä käytetään elementtien muistissa pitämiseen. Pinoa ei siis kannata yrittää testata ennenkuin olet saanut toteutettua sen kaikki metodit.

Huomaa että toteutuksessa käytetään geneerisiä luokkia template -parametrilla `E` joka esitellään rajapintaluokassa `StackInterface`:

```Java
public class StackImplementation<E> implements StackInterface<E> {
```

Pino on siis geneerinen tietorakenneluokka, jonne voidaan laittaa mitä vaan luokkien olioita. Vaatimusta esim. `Comparable` -rajapinnan toteuttamiseen ei tässä tarvita.

Tässä tehtävässä toteuta pinon sisäinen tietorakenne tavallisilla Javan taulukoilla, joka sisältää pinoon laitettavat oliot:

```Java
private Object [] itemArray;
```

Koska *kaikki* Javan luokat perivät viime kädessä luokan `Object`, voimme luoda taulukon joka sisältää näitä `Object` -luokan olioita (oikeastaan mitä vaan sen aliluokkia siis).

Tarvitset pinon toteutuksessa seuraavia jäsenmuuttujia:

* Taulukko, jossa elementit ovat.
* Kokonaislukumuuttuja joka kertoo indeksin johon viimeisin elementti on lisätty.

Luokan `StackImplemention` muodostimessa (constructor), toteuta se, että pino varaa muistia taulukkoon näitä pinon elementtejä varten *oletuskapasiteetin* verran. Esimerkiksi näin:

```Java
   itemArray = new Object[DEFAULT_STACK_SIZE];
```

Muodostin luo tilaa vain rajatulle määrälle olioita. Sinun täytyy huolehtia siitä, että jos tämä tila loppuu kesken kun pinon `push()` -metodia kutsutaan, siellä tarpeen vaatiessa *reallokoidaan lisää tilaa* uusien olioiden lisäämiseksi uudelleen allokoituun isompaan taulukkoon! Tämä reallokointi kannattaa tehdä omaan pikku privaattiin metodiin jota kutsutaan `push` -metodista tarvittaessa. Muista kopioida tiedot vanhasta taulukosta uuteen ennenkuin korvaat vanhan jäsenmuuttujataulukon uudella.

Tyypillinen toteutus luo tilaa oletusarvoisesti esimerkiksi 20 elementille. Jotkut tykkäävät käyttää alkuarvona 32:ta. Älä käytä liian suurta oletuskokoa, sillä se tuhlaa muistia tilanteissa joissa pinoon laitetaan vain muutamia olioita. Vastaavasti reallokoinnissa, kannattaa yleensä vaikka tuplata muistitila. Jos alkuarvo on ollut 32, tuplaten siitä tulee 64, sitten 128, sitten 256 eli mukavasti kasvu tapahtuu kahden potensseina. 

Jos kasvatat tilaa kitsaasti, pino joutuu sinne olioita lisätessä jatkuvasti reallokoimaan. Älä esimerkiksi kasvata taulukon kokoa vain yhdellä. Tällöin jos taulukkoon lisätään tuhansia olioita, ensimmäisen reallokoinnin jälkeen *joka ikisen* uuden elementin lisääminen pinoon johtaa reallokointiin. Tämä hidastaa toteutusta tarpeettomasti.

Voit määritellä oletuskoon vakiona pino -luokan jäsenmuuttujana:

```Java
   private static final int DEFAULT_STACK_SIZE = 20;
```

Voit käyttää tätä vakiota nyt sitten joka paikassa jossa luodaan oletuskokoinen pinon taulukko, esimerkiksi:

```Java
   itemArray = new Object[DEFAULT_STACK_SIZE];
```
Näin on helppoa muuttaa tuo oletuskoko, kun joka paikassa koodissa käytetään samaa vakiota, vain muuttamalla tuon vakion arvo tuossa yhdessä paikassa missä se esitellään. Tämä on hyvää koodauskäytäntöä. 

Koodissa pitäisi aina välttää ns. "maagisia" numeroita, eli jos siellä täällä on numeroarvoja `10`, tai `42`, tai mitään muutakaan, ne kannattaa korvata vakiomuuttujilla jotka selkeästi kertovat mikä on tuon numeron *tarkoitus* koodissa. Näin koodista tulee a) luettavampaa ja b) vakioiden joita käytetään useassa paikassa, muuttaminen on helppoa kun se tehdään vain *yhteen* paikkaan.

**Toteuta** ylikuormittaen (`@Override`) pinoosi myös `Object` luokasta perittävä metodi `public String toString()`:

```Java
@Override
public String toString() {
   ...
```
Siten että palautettu merkkijono sisältää pinon sisällön alkaen "pohjalta", *täsmälleen* seuraavassa formaatissa, pilkut ja välilyönnit mukaanlukien:

```text
[110, 119, 121]
```

Esimerkkipinon sisältö on luotu kutsumalla `stack.push(110)`, `stack.push(119)` ja `stack.push(121)`. Metodin `pop()` kutsu palauttaisi tässä tilanteessa arvon 121.

Tyhjä pino palauttaa merkkijonon "[]". Huomaa että pino voi sisältää *mitä tahansa* Java -luokkia, muutakin kuin Integer -olioita! Jos merkkijono ei ole *täsmälleen* määrittelyn mukainen, testit epäonnistuvat.

Kun olet valmis testaamaan toteutustasi, **Luo** toteuttamasi pino-olio `oy.interact.tira.factories.StackFactory` -luokan eri metodeissa ja palauta toteutus kutsujalle. Testit ja muu annettu koodi saavat pinon toteutuksesi käsiinsä vain tämän tehdasluokan kautta, joten jos tämä unohtuu tehdä, testit epäonnistuvat. 

Jos sinulla ei ole tarvittavaa *kahta* muodostinta pino-luokassasi, tee ne:

1. Parametriton versio luo pinon taulukon *oletuskapasiteetin* kokoiseksi.
2. Parametrillinen versio (int) luo taulukon sen kokoiseksi kun parametri kertoo.

Muistaa antaa kaikille jäsenmuuttujille järkevät arvot muodostimissa. Tarkista myös että parametreina annetut muuttujat ovat järkeviä, ja heitä tarvittaessa poikkeus. Rajapintaluokan metodien esittelyt antavat vihjeitä siitä mitä poikkeuksia kukin metodi voisi heittää.

**Testaa** toteutustasi ensin vain testiluokan `StackTests` kautta. Testi löytyy hakemistosta `grade_1/task_4`.

Jos kaikki toimii, voit edetä seuraavaan askeleeseen.

> Huomaa että kurssilla kiertää vanhoja ratkaisuja ja C++ -demosta peräisin olevia ratkaisuja joita Javassa ei tarvita. Yksi näistä on se, että pinossa on jäsenmuuttujana kokonaislukumuuttuja `capacity` joka kertoo pinon kapasiteetin. Kun pinon sisäisen taulukon koko muuttuu, silloin tämän `capacity` -muuttujankin arvoa pitää päivittää. Jos tässä tekee virheitä, pinon tila menee sekaisin. Javassa kapasiteettina voi ihan hyvin käyttää taulukon kokoa, joka tekee koodista yksinkertaisempaa, lyhyempää ja vähemmän virhealtista. Älä siis matki muiden virheitä tai C++ -demoa -- siinä kielessä taulukoilla ei ole moista Javan ominaisuutta kuin pituus.


## Askel 2 - Ohjeet

Tässä askeleessa pinoa käytetään tarkistamaan rakenteisen tekstin oikeellisuutta siten että sulkuja on tekstissä on oikea määrä ja avaavat ja sulkevat s sulut ovat tekstissä oikein. Toteutat siis luokan `ParenthesisChecker` valmiiksi, runko siitä on annettu valmiina.

**Arvosanaa 1** varten **ei tarvitse huomioida** tekstissä olevien **lainausmerkkien** välissä olevaa tekstiä (toisin kuin vuoden 2023 toteutuksessa). Arvosanan 1 testiaineisto **ei sisällä** sellaisia lainausmerkkien välissä olevia merkkjonoja joissa olisi sulkuja. Toteutuksesi ei tarvitse siis mitenkään huomioida sitä että analysoitavassa tekstissä voi olla lainausmerkkejä, eli voit vain tutkia onko käsiteltävä merkki avaava tai sulkeva sulku. Lopussa on ohje jos haluat nämä lainausmerkit huomioida, miten se tehdään arvosanaan 2 tai 3, joissa tämä asia taas *vaaditaan*.

Sovelluksessa löytyy valmiina luokka `oy.interact.tira.student.ParenthesisChecker`.  Sen metodi `checkParentheses(StackInterface<Character>, String))` on toteuttamatta.

**Toteuta** metodi seuraten tässä ja koodin kommenteissa olevia ohjeita. 

Merkkijonon käsittelyn **aikakompleksisuusluokan** tulee olla O(n), jossa n on merkkijonon pituus. Suomeksi, käy merkkijono merkki kerrallaan alusta loppuun, ja tarkista sulkujen oikeellisuus pinoa hyödyntäen.

Toteutuksen **muistikompleksisuuden** pitää olla O(1). Algoritmi ei siis saa varata lisää muistia, pieniä muuttujia enempää. Erityisesti, jos käsittelet merkkijonon merkkejä silmukassa tyyliin...

```Java
   for (char oneChar : fromString.toCharArray()) { 
```

...tämä **tuplaa** algoritmin muistinkulutuksen, ja tämä on **erittäin** huono ratkaisu. 

Suurimpaa Coder -JSON -tiedostoa käsitellessä `toCharArray`:n käyttö muodostaa todellisen ongelman. Tiedoston koko on n. 870 Mt, ja jos teet tällä tavalla, kaikki tiedoston merkit ovat *sekä* `fromString` -merkkijonossa, *että* `toCharArray()`n palauttamassa `char []` -taulukossa, joten tiedoston merkit ovat muistissa *kaksi* kertaa. Näin ollen analysoitaessa tätä tiedostoa muistinkulutus kasvaa n. 870 Mt:sta 1.7 Gigatavuun!!! Todennäköisesti koneestasi loppuu muisti jos yrität tällaisella toteutuksella tuota tiedostoa analysoida, vaikka se voisi muuten riittää.

Lue huolellisesti metodin `ParenthesisChecker.checkParentheses` dokumentaatio. Se kuvaa miten tarkistetaan, hyödyntäen parametrina tullutta valmiiksi luotua pino-oliota, onko toinen merkkijonoparametri (joka sisältää rakenteista tekstiä jossa on sulkuja) oikeellinen. 

Suluiksi tässä tapauksessa lasketaan vain nämä merkit: `({[]})`. Tämä on samalla esimerkki *oikeellisista* suluista rakenteellisen tekstin merkkijonossa. 

Tässää sen sijaan on esimerkkejä sulkurykelmistä joista checkerin pitäisi antaa virhe:

* `[ [ ( ) ]` -- sulkevia sulkuja liian vähän, lopusta puuttuu `]`.
* `[ ( { ) } ]` - kolmas sulku avaa lohkon `{` mutta sen sulkee `)` -- väärä sulku.

Lisää esimerkkejä löydät testiluokasta ja sen käyttämästä testiaineistosta.

Heti kun tarkistin huomaa, että joku sulku on väärin tai puuttuu, sen pitää heittää poikkeusolio `ParenthesesException` joka on valmiiksi toteutettu luokka.

Huomaa, että poikkeusolion täytyy sisältää tietoa siitä missä kohtaa tekstissä huomattiin että asiat ovat pielessä. Tässä esimerkki tavasta jolla virhe heitetään:

```Java
   throw new ParenthesesException("Parenthesis didn't match.", lineNumber, columnNumber, ParenthesesException.PARENTHESES_IN_WRONG_ORDER);
```

Parametrit poikkeusolion muodostimessa ovat:

1. Sanallinen lyhyt kuvaus siitä mikä meni pieleen, tarkoitettu virheilmoitukseksi käyttäjälle. Mieti tarkkaan mitä virheilmoituksissa kannattaa ja pitäisi sanoa.
2. Tekstin rivinumero (`lineNumber`) jolla virhe huomattiin. Teksti sisältää rivinvaihtomerkkejä `\n`, joiden avulla tiedetää monennellako rivillä ollaan menossa.
3. Monesko merkki eli sarake (`columnNumber`) rivillä oli se kohta jossa huomattiiin että asiat ovat pielessä.
4. Virheen koodi, jonka avulla tarkistimen kutsuja voi myös ohjelmallisesti tarkistaa mikä oli vikana annetussa merkkijonossa ja missä kohtaa. Nämä ovat vakioita jotka on määritelty `ParenthesesException` -luokassa. Käytä niitä oikein, sillä testit tarkastelevat onko poikkeuksen sisältämä virhekoodi (sekä rivi- ja sarakenumerot) oikea.

Rivi- ja sarakenumerot alkavat luonnollisesti 1:stä, eivät 0:sta.

Jos tarkistin ei löydä ongelmia, sen tulee palauttaa sulkevien ja avaavien sulkujen yhteismäärä.

## Testaus

Kun testaat `ParenthesisChecker`:n toteutusta testeillä luokassa `grade_1/task_4/ParenthesisTests`, huomaa että jos joudut korjaamaan pino-toteutustasi, kannattaa ehdottomasti ajaa myös pinoa testaavat testit uudelleen. Voit nimittäin korjauksia tehdessä tehdä uusia bugeja. Toivottavasti `StackTests` -testit löytävät ne. 

Koodia kun muuttaa, aina pitää testata jo aiemmin testatuilla testeillä toteutus uudestaan. Tätä kutsutaan **regressiotestaamiseksi**.

Kun testit menevät läpi, voit myös testata toteutustasi TIRA Coders -sovelluksessa! Valitse sovelluksen valikosta TIRA Coders > Check JSON file -komento. Avaa mikä tahansa .json tiedosto ja tarkista ovatko sulut oikein. Kun purit hakemasi `TIRACoders.zip` -tiedoston (tehtävä 1, askel 2), sen mukana tuli myös kaksi "viallista" tiedostoa, `faulty.json` ja `faulty-2.json`. Tarkista että niistä *tulee* virheilmoitus.

Voit myös avata minkä tahansa Java -tiedoston kun valitset tiedostonvalintadialogissa tiedoston tyypiksi All files. Jos kooditiedostossa on sulut kohdallaan, saat siitä tiedon, tai jos sulut ovat väärin, siitäkin ilmoitetaan. Voit itse kokeilla tekemällä sekä oikeellisia että virheellisiä omia Java ja JSON -tiedostoja ja kokeilla lisää.

Nyt sovelluksen käyttäjät voivat tarkistaa jos joku datatiedostoista on pielessä! Tai vaikka jos joku oma kooditiedostosi on sellainen että sulkujen kanssa on ongelmia.

## Raportti

**Kirjaa** raporttiisi `RAPORTTI.markdown` mitä opit tehtävän tekemisestä, mikä oli vaikeaa, mikä helppoa, jne.

Analysoi erityisesti sitä, onko toteutuksesi sellainen että se vastaa tehtävän alussa esiteltyjä aikakompleksisuusvaatimuksia. **Perustele** miksi, jokaisen pinon algoritmin kohdalla *erikseen*.

> Mikä tahansa metodi jossa on *silmukka*, ei *voi* olla O(1) -- onko sinulla silmukoita metodeissa joissa vaatimus oli O(1)? Tai kutsutko tällaisesta metodista jotain *toista* metodia jonka O on muuta kuin O(1)? Jos näin on, aikakompleksisuusvaatimus ei täyty.


## Arvosanat 2 ja 3

Arvosana 2:

Tällainen rivi JSON -datassa on ihan validia JSON:ia:

```json
       "number": "646 [555-4567"
```
Mutta jos toteutuksesi yllä olevia ohjeita seuraten ei skipannut lainausmerkkien välissä oleviä merkkejä, vaan laski tuon avaavaksi hakasuluksi, toteutus ei huomioi sitä että lainausmerkeissä *saa olla* sulkuja ihan miten sattuu.

Voit kokeilla miten käy kun suoritat testin `grade_2/task_4/ParenthesisSingleLineQuotationsTests`. Testissä on aineistoja joissa lainausmerkkien välissä *on* epätasapainoisesti sulkuja. Jos et huomioinut lainausmerkkejä ja ignoorannut niiden välissä olevaa tekstiä toteutuksessasi, testi ei mene läpi.

Arvosanaa 2 varten, **paranna** `ParenthesisChecker`:n toteutusta niin että se osaa ottaa huomioon **yhdellä rivillä** (kuten esimerkki yllä) olevien lainausmerkkien välissä olevat sulut ignooraten ne. Niitä ei siis huomioida. Käytännössä, muuta algoritmia niin että se ei millään tavalla huomioi mitään merkkejä lainausmerkkien välissä, ainoastaan ylläpitää sarakelaskuria.

Eli aina kun tulee vastaan merkki `"`, siitä eteenpäin kaikki merkit (myös sulut) ohitetaan kunnes samalla rivillä tulee vastaan toinen merkki `"`, jolloin taas merkkejä tutkitaan sulkujen varalta. Laskettaessa rivejä ja sarakkeita, nämä merkit kuitenkin luonnollisesti huomioidaan, muuten tarkistin ei voi näyttää oikeaa rivi- ja sarakenumeroa jos tekstistä löytyy sulkuvirhe.

Jos tähtäät **arvosanaan 3**:

Tarkistimen tulee **jättää huomiotta** kaikki lainausmerkeissä **useammalla rivillä** olevat osat tekstistä. Javassa (kuten monissa muissakin ohjelmointikielissä), voidaan esittää monirivisiä merkkijonoliteraaleja näin:

```Java
    private static final String multiline = """
            This is a 
            string on multiple lines that is
            syntactically legal in Java
            and many other languages.
            This string may also contain parentheses like [ 
            and ) without problems since they do not count.
            """;    
```

Eli merkkijono alkaa *kolmella* lainausmerkillä, jonka jälkeen varsinainen teksti on omilla riveillään, ja merkkijono loppuu taas omalla rivillään olevaan kolmeen lainausmerkkiin. Huomaa että viimeisten lainausmerkkien ei tarvitse olla rivin alussa.

**Paranna** `ParenthesisChecker`n toteutusta niin että se läpäisee testit hakemistossa `grade_3/task_4` eli ottaa huomioon sen että tekstissä voi olla useamman rivin lainausmerkeissä tekstissä sulkujakin.

Huomaa että *myös lainausmerkit* voivat olla merkkijonossa *väärin*, niitä voi olla liian vähän tai liikaa, joten tarkastele testien testiaineistoa ja varmista että otat nämäkin tilanteet huomioon toteutuksessasi ja että toteutuksesi läpäisee ainakin nämä testien tilanteet!

Jos teet nämä arvosanan 2 ja/tai 3 toteutukset, kerro myös näistä toteutuksista raportissasi.

## Lopuksi

Kun olet valmis, varmista että sekä raportti että kaikki uusi ja muuttunut koodi on paikallisessa git -repositoryssäsi ja myös etärepositoryssäsi (komennot `git commit`, tarvittaessa uusille tiedostoille `git add` sekä `git push`).

## Tietoja

* Kurssimateriaalia Tietorakenteet ja algoritmit -kurssille | Data structures and algorithms 2021-2024.
* Tietojenkäsittelytieteet, Tieto- ja sähkötekniikan tiedekunta, Oulun yliopisto.
* (c) Antti Juustila 2021-2024, INTERACT Research Group.