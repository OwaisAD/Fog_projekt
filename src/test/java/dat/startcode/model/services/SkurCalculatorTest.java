package dat.startcode.model.services;

import dat.startcode.model.dtos.ProduktDTO;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.exceptions.IllegalDimensionException;
import dat.startcode.model.persistence.ConnectionPool;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SkurCalculatorTest {

    CarportCalculator calculator;
    String user = "root";
    String password = "DiskoT@ngo";
    String url = "jdbc:mysql://localhost:3306/fog";
    ConnectionPool connectionPool = new ConnectionPool(user,password,url);
    List<ProduktDTO> pDTO;

    {
        try {
            pDTO = ProductFacade.getProduktDTOs(connectionPool);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    void setUp() {
        calculator = new CarportCalculator(pDTO);
    }

    @Test //Udregner antal og længde på løsholter på dørside i kort skur
    void testBeregnAntalLøsholterDørSideKort() throws IllegalDimensionException {
        calculator.beregnCarport(700,600,210,"y","p",
                "midt",3);
        assertEquals(1, calculator.skur.løsholterLangsideMedDørAntal);
        assertEquals(240, calculator.skur.løsholterLangsideMedDørLængde);
    }

    @Test //Udregner antal og længde på løsholter på dørside i mellemlangt skur
    void testBeregnLængdeLøsholterDørSideMellemLang() throws IllegalDimensionException {
        calculator.beregnCarport(700,600,210,"y","p",
                "midt",4);
        assertEquals(1, calculator.skur.løsholterLangsideMedDørAntal);
        assertEquals(360, calculator.skur.løsholterLangsideMedDørLængde);

        calculator = new CarportCalculator(pDTO);
        calculator.beregnCarport(840,600,210,"y","p",
                "midt",6);
        System.out.println("Længde af hvert lille stykke mellem dør og stolpe "+(6-2)* calculator.skur.afstandMellemSpær);
        assertEquals(3, calculator.skur.løsholterLangsideMedDørAntal);
        assertEquals(240, calculator.skur.løsholterLangsideMedDørLængde);

        calculator = new CarportCalculator(pDTO);
        calculator.beregnCarport(840,600,210,"y","p",
                "midt",7);
        System.out.println("Længde af hvert lille stykke mellem dør og stolpe "+(7-2)* calculator.skur.afstandMellemSpær);
        assertEquals(3, calculator.skur.løsholterLangsideMedDørAntal);
        assertEquals(300, calculator.skur.løsholterLangsideMedDørLængde);
    }

    @Test //Udregner antal og længde på løsholter på dørside i langt skur
    void testBeregnAntalLøsholterDørSideLang() throws IllegalDimensionException {
        calculator.beregnCarport(1000,600,210,"y","p",
                "midt",10);
        System.out.println("Da der nu er væg>310 i dør side isættes ekstra stolpe, den samlede afstand deles afstand og hvert stykke skal være mindst "+((10-2)/2)* calculator.skur.afstandMellemSpær);
        assertEquals(6, calculator.skur.løsholterLangsideMedDørAntal);
        assertEquals(240, calculator.skur.løsholterLangsideMedDørLængde);
    }

    @Test //Udregner antal og længde på løsholter modsat dørside i kort skur
    void testBeregnAntalLøsholterModsatDørSideKort() throws IllegalDimensionException {
        calculator.beregnCarport(700,600,210,"y","p",
                "midt",3);
        assertEquals(3, calculator.skur.løsholterLangsideUdenDørAntal);
        assertEquals(240, calculator.skur.løsholterLangsideUdenDørLængde);
    }

    @Test //Udregner antal og længde på løsholter modsat dørside i mellemlangt skur
    void testBeregnLængdeLøsholterModsatDørSideMellemLang() throws IllegalDimensionException {
        calculator.beregnCarport(700,600,210,"y","p",
                "midt",4);
        assertEquals(3, calculator.skur.løsholterLangsideUdenDørAntal);
        assertEquals(240, calculator.skur.løsholterLangsideUdenDørLængde);


        calculator = new CarportCalculator(pDTO);
        calculator.beregnCarport(820,600,210,"y","p",
                "midt",5);
        assertEquals(3, calculator.skur.løsholterLangsideUdenDørAntal);
        assertEquals(300, calculator.skur.løsholterLangsideUdenDørLængde);
    }

    @Test //Udregner antal og længde på løsholter på dørside i langt skur
    void testBeregnLængdeLøsholterModsatDørSideLang() throws IllegalDimensionException {
        calculator.beregnCarport(790,600,210,"y","p",
                "midt",6);
        assertEquals(6, calculator.skur.løsholterLangsideUdenDørAntal);
        assertEquals(240, calculator.skur.løsholterLangsideUdenDørLængde);

        calculator = new CarportCalculator(pDTO);
        calculator.beregnCarport(960,600,210,"y","p",
                "midt",9);
        assertEquals(6, calculator.skur.løsholterLangsideUdenDørAntal);
        assertEquals(270, calculator.skur.løsholterLangsideUdenDørLængde);
    }

    @Test //Udregner antal og længde på løsholter på bredside (Bredde < 240 og bredde op til 310)
    void testBeregnAntalLøsholterBredsideSmalt() throws IllegalDimensionException {
        calculator.beregnCarport(700,450,210,"y","p",
                "venstre",3);
        assertEquals(6, calculator.skur.løsholterBredsideAntal);
        assertEquals(240, calculator.skur.løsholterBredsideLængde);

        calculator = new CarportCalculator(pDTO);
        calculator.beregnCarport(700,540,210,"y","p",
                "venstre",3);
        assertEquals(6, calculator.skur.løsholterBredsideAntal);
        assertEquals(240, calculator.skur.løsholterBredsideLængde);
    }

    @Test //Udregner antal og længde på løsholter på bredside (Bredde mere end 310 og mindre end 480)
    void testBeregnAntalLøsholterBredsideMellemBred() throws IllegalDimensionException {
        calculator.beregnCarport(700,450,210,"y","p",
                "midt",3);
        assertEquals(12, calculator.skur.løsholterBredsideAntal);
        assertEquals(240, calculator.skur.løsholterBredsideLængde);
    }

    @Test //Udregner antal og længde på løsholter på bredside (Bredde mere end 480)
    void testBeregnAntalLøsholterBredsideBred() throws IllegalDimensionException {
        calculator.beregnCarport(700,500,210,"y","p",
                "midt",4);
        assertEquals(12, calculator.skur.løsholterBredsideAntal);
        assertEquals(240, calculator.skur.løsholterBredsideLængde);
    }

    @Test //Udregner længde og antal beklædningsbrædder
    void testBeregenAntalBeklædningsBrædder() throws IllegalDimensionException {
        //Da inder og yderbrædder er ens, regnes det samlede antal ud.
        calculator.beregnCarport(780,600,210,"y","p",
                "midt",4);
        assertEquals(187, calculator.skur.beklædningsBrædderAntal);
        assertEquals(210, calculator.skur.skurHøjde);
    }

    @Test //Udregner hvor mange pakker skruer der skal til inderbeklædningsbrædderne
    void testBeregnAntalKorteBeklædningsSkruer() throws IllegalDimensionException{
        calculator.beregnCarport(780,600,210,"y","p",
                "midt",4);
        assertEquals(3, calculator.skur.pakkerKorteBeklædningsSkruerAntal);
    }

    @Test //Udregner hvor mange pakker skruer der skal til yderbeklædningsbrædderne
    void testBeregnAntalLangeBeklædningsSkruer() throws IllegalDimensionException{
        calculator.beregnCarport(780,600,210,"y","p",
                "midt",4);
        assertEquals(2, calculator.skur.pakkerLangeBeklædningsSkruerAntal);
    }

    @Test
    void testAddAllSkurItemsTilListe() throws IllegalDimensionException{
        calculator.beregnCarport(780,600,210,"y","p",
                "midt",4);
        assertEquals(9, calculator.skur.skurList.size());
    }
}