package com.github.lowkkid.lodgecore.common.data.provider;

import com.github.lowkkid.lodgecore.guest.domain.entity.Guest;
import java.util.Arrays;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MockGuestsProvider {
    public static final Guest JOAO_SILVA = Guest.builder()
            .fullName("João Silva")
            .email("joao.silva@example.com")
            .nationalId("PT123456789")
            .build();

    public static final Guest MARIA_SANTOS = Guest.builder()
            .fullName("Maria Santos")
            .email("maria.santos@example.com")
            .nationalId("PT987654321")
            .build();

    public static final Guest CARLOS_OLIVEIRA = Guest.builder()
            .fullName("Carlos Oliveira")
            .email("carlos.oliveira@example.com")
            .nationalId("PT456789123")
            .build();

    public static final Guest OLIVER_JOHNSON = Guest.builder()
            .fullName("Oliver Johnson")
            .email("oliver.johnson@example.com")
            .nationalId("GB123456789")
            .build();

    public static final Guest CHARLOTTE_WILLIAMS = Guest.builder()
            .fullName("Charlotte Williams")
            .email("charlotte.williams@example.com")
            .nationalId("GB987654321")
            .build();

    public static final Guest MIKA_HAKKINEN = Guest.builder()
            .fullName("Mika Hakkinen")
            .email("mika.hakkinen@example.com")
            .nationalId("FI123456789")
            .build();

    public static final Guest EMMA_KORHONEN = Guest.builder()
            .fullName("Emma Korhonen")
            .email("emma.korhonen@example.com")
            .nationalId("FI987654321")
            .build();

    public static final Guest HANS_MULLER = Guest.builder()
            .fullName("Hans Müller")
            .email("hans.muller@example.com")
            .nationalId("DE123456789")
            .build();

    public static final Guest SOPHIE_SCHMIDT = Guest.builder()
            .fullName("Sophie Schmidt")
            .email("sophie.schmidt@example.com")
            .nationalId("DE987654321")
            .build();

    public static final Guest LUKAS_FISCHER = Guest.builder()
            .fullName("Lukas Fischer")
            .email("lukas.fischer@example.com")
            .nationalId("DE456789123")
            .build();

    public static final Guest CARLOS_MAMANI = Guest.builder()
            .fullName("Carlos Mamani")
            .email("carlos.mamani@example.com")
            .nationalId("BO123456789")
            .build();

    public static final Guest MARIA_QUISPE = Guest.builder()
            .fullName("Maria Quispe")
            .email("maria.quispe@example.com")
            .nationalId("BO987654321")
            .build();

    public static final Guest MICHAEL_SMITH = Guest.builder()
            .fullName("Michael Smith")
            .email("michael.smith@example.com")
            .nationalId("US123456789")
            .build();

    public static final Guest JENNIFER_DAVIS = Guest.builder()
            .fullName("Jennifer Davis")
            .email("jennifer.davis@example.com")
            .nationalId("US987654321")
            .build();

    public static final Guest ROBERT_WILSON = Guest.builder()
            .fullName("Robert Wilson")
            .email("robert.wilson@example.com")
            .nationalId("US456789123")
            .build();

    public static final Guest MOHAMED_ALI = Guest.builder()
            .fullName("Mohamed Ali")
            .email("mohamed.ali@example.com")
            .nationalId("EG123456789")
            .build();

    public static final Guest FATIMA_HASSAN = Guest.builder()
            .fullName("Fatima Hassan")
            .email("fatima.hassan@example.com")
            .nationalId("EG987654321")
            .build();

    public static final Guest ANTONIO_GARCIA = Guest.builder()
            .fullName("Antonio García")
            .email("antonio.garcia@example.com")
            .nationalId("ES123456789")
            .build();

    public static final Guest ISABEL_RODRIGUEZ = Guest.builder()
            .fullName("Isabel Rodríguez")
            .email("isabel.rodriguez@example.com")
            .nationalId("ES987654321")
            .build();

    public static final Guest JAVIER_LOPEZ = Guest.builder()
            .fullName("Javier López")
            .email("javier.lopez@example.com")
            .nationalId("ES456789123")
            .build();

    public static final Guest WEI_ZHANG = Guest.builder()
            .fullName("Wei Zhang")
            .email("wei.zhang@example.com")
            .nationalId("CN123456789")
            .build();

    public static final Guest LI_WANG = Guest.builder()
            .fullName("Li Wang")
            .email("li.wang@example.com")
            .nationalId("CN987654321")
            .build();

    public static final Guest MEI_CHEN = Guest.builder()
            .fullName("Mei Chen")
            .email("mei.chen@example.com")
            .nationalId("CN456789123")
            .build();

    public static final Guest AHMED_IBRAHIM = Guest.builder()
            .fullName("Ahmed Ibrahim")
            .email("ahmed.ibrahim@example.com")
            .nationalId("SD123456789")
            .build();

    public static final Guest AISHA_MOHAMED = Guest.builder()
            .fullName("Aisha Mohamed")
            .email("aisha.mohamed@example.com")
            .nationalId("SD987654321")
            .build();

    public static final Guest PEDRO_SANTOS = Guest.builder()
            .fullName("Pedro Santos")
            .email("pedro.santos@example.com")
            .nationalId("BR123456789")
            .build();

    public static final Guest ANA_SILVA = Guest.builder()
            .fullName("Ana Silva")
            .email("ana.silva@example.com")
            .nationalId("BR987654321")
            .build();

    public static final Guest JUAN_HERNANDEZ = Guest.builder()
            .fullName("Juan Hernández")
            .email("juan.hernandez@example.com")
            .nationalId("MX123456789")
            .build();

    public static final Guest SOFIA_GONZALEZ = Guest.builder()
            .fullName("Sofia González")
            .email("sofia.gonzalez@example.com")
            .nationalId("MX987654321")
            .build();

    public static final Guest DIEGO_RODRIGUEZ = Guest.builder()
            .fullName("Diego Rodríguez")
            .email("diego.rodriguez@example.com")
            .nationalId("MX456789123")
            .build();

    public static final Guest ALI_KHAN = Guest.builder()
            .fullName("Ali Khan")
            .email("ali.khan@example.com")
            .nationalId("PK123456789")
            .build();

    public static final Guest SAIMA_AHMED = Guest.builder()
            .fullName("Saima Ahmed")
            .email("saima.ahmed@example.com")
            .nationalId("PK987654321")
            .build();

    public static final Guest JACK_THOMPSON = Guest.builder()
            .fullName("Jack Thompson")
            .email("jack.thompson@example.com")
            .nationalId("AU123456789")
            .build();

    public static final Guest OLIVIA_WILSON = Guest.builder()
            .fullName("Olivia Wilson")
            .email("olivia.wilson@example.com")
            .nationalId("AU987654321")
            .build();

    public static final Guest NOAH_BROWN = Guest.builder()
            .fullName("Noah Brown")
            .email("noah.brown@example.com")
            .nationalId("AU456789123")
            .build();

    public static final Guest PIERRE_DUBOIS = Guest.builder()
            .fullName("Pierre Dubois")
            .email("pierre.dubois@example.com")
            .nationalId("FR123456789")
            .build();

    public static final Guest MARIE_LAURENT = Guest.builder()
            .fullName("Marie Laurent")
            .email("marie.laurent@example.com")
            .nationalId("FR987654321")
            .build();

    public static final Guest THOMAS_MOREAU = Guest.builder()
            .fullName("Thomas Moreau")
            .email("thomas.moreau@example.com")
            .nationalId("FR456789123")
            .build();

    public static final Guest RAJ_PATEL = Guest.builder()
            .fullName("Raj Patel")
            .email("raj.patel@example.com")
            .nationalId("IN123456789")
            .build();

    public static final Guest PRIYA_SHARMA = Guest.builder()
            .fullName("Priya Sharma")
            .email("priya.sharma@example.com")
            .nationalId("IN987654321")
            .build();

    public static final Guest AMIT_KUMAR = Guest.builder()
            .fullName("Amit Kumar")
            .email("amit.kumar@example.com")
            .nationalId("IN456789123")
            .build();

    public static final Guest KHALID_AL_MOHAMMED = Guest.builder()
            .fullName("Khalid Al-Mohammed")
            .email("khalid.almohammed@example.com")
            .nationalId("KW123456789")
            .build();

    public static final Guest NOURA_AL_SABAH = Guest.builder()
            .fullName("Noura Al-Sabah")
            .email("noura.alsabah@example.com")
            .nationalId("KW987654321")
            .build();

    public static final Guest THABO_MBEKI = Guest.builder()
            .fullName("Thabo Mbeki")
            .email("thabo.mbeki@example.com")
            .nationalId("ZA123456789")
            .build();

    public static final Guest NOMSA_ZULU = Guest.builder()
            .fullName("Nomsa Zulu")
            .email("nomsa.zulu@example.com")
            .nationalId("ZA987654321")
            .build();

    public static final Guest SIPHO_NDLOVU = Guest.builder()
            .fullName("Sipho Ndlovu")
            .email("sipho.ndlovu@example.com")
            .nationalId("ZA456789123")
            .build();

    public static final Guest YUKI_TANAKA = Guest.builder()
            .fullName("Yuki Tanaka")
            .email("yuki.tanaka@example.com")
            .nationalId("JP123456789")
            .build();

    public static final Guest HIROSHI_SATO = Guest.builder()
            .fullName("Hiroshi Sato")
            .email("hiroshi.sato@example.com")
            .nationalId("JP987654321")
            .build();

    public static final Guest AIKO_YAMAMOTO = Guest.builder()
            .fullName("Aiko Yamamoto")
            .email("aiko.yamamoto@example.com")
            .nationalId("JP456789123")
            .build();

    public static final Guest ABDULLAH_AL_SAUD = Guest.builder()
            .fullName("Abdullah Al-Saud")
            .email("abdullah.alsaud@example.com")
            .nationalId("SA123456789")
            .build();

    public static final Guest SARAH_AL_RASHID = Guest.builder()
            .fullName("Sarah Al-Rashid")
            .email("sarah.alrashid@example.com")
            .nationalId("SA987654321")
            .build();

    public static final Guest MINH_NGUYEN = Guest.builder()
            .fullName("Minh Nguyen")
            .email("minh.nguyen@example.com")
            .nationalId("VN123456789")
            .build();

    public static final Guest LAN_TRAN = Guest.builder()
            .fullName("Lan Tran")
            .email("lan.tran@example.com")
            .nationalId("VN987654321")
            .build();

    public static final Guest HUY_PHAM = Guest.builder()
            .fullName("Huy Pham")
            .email("huy.pham@example.com")
            .nationalId("VN456789123")
            .build();

    public static final Guest MIN_HO_KIM = Guest.builder()
            .fullName("Min-ho Kim")
            .email("minho.kim@example.com")
            .nationalId("KR123456789")
            .build();

    public static final Guest JI_EUN_PARK = Guest.builder()
            .fullName("Ji-eun Park")
            .email("jieun.park@example.com")
            .nationalId("KR987654321")
            .build();

    public static final Guest SEUNG_HYUN_LEE = Guest.builder()
            .fullName("Seung-hyun Lee")
            .email("seunghyun.lee@example.com")
            .nationalId("KR456789123")
            .build();

    public static final Guest CARLOS_RAMIREZ = Guest.builder()
            .fullName("Carlos Ramirez")
            .email("carlos.ramirez@example.com")
            .nationalId("CO123456789")
            .build();

    public static final Guest CAMILA_RODRIGUEZ = Guest.builder()
            .fullName("Camila Rodríguez")
            .email("camila.rodriguez@example.com")
            .nationalId("CO987654321")
            .build();

    public static final Guest LIAM_TREMBLAY = Guest.builder()
            .fullName("Liam Tremblay")
            .email("liam.tremblay@example.com")
            .nationalId("CA123456789")
            .build();

    public static final Guest EMMA_MARTIN = Guest.builder()
            .fullName("Emma Martin")
            .email("emma.martin@example.com")
            .nationalId("CA987654321")
            .build();

    public static final Guest NOAH_GAGNON = Guest.builder()
            .fullName("Noah Gagnon")
            .email("noah.gagnon@example.com")
            .nationalId("CA456789123")
            .build();

    public static final Guest DIEGO_GONZALEZ = Guest.builder()
            .fullName("Diego González")
            .email("diego.gonzalez@example.com")
            .nationalId("AR123456789")
            .build();

    public static final Guest VALENTINA_LOPEZ = Guest.builder()
            .fullName("Valentina López")
            .email("valentina.lopez@example.com")
            .nationalId("AR987654321")
            .build();

    public static final Guest JUAN_PEREZ = Guest.builder()
            .fullName("Juan Pérez")
            .email("juan.perez@example.com")
            .nationalId("AR456789123")
            .build();

    public static final Guest CHINEDU_OKEKE = Guest.builder()
            .fullName("Chinedu Okeke")
            .email("chinedu.okeke@example.com")
            .nationalId("NG123456789")
            .build();

    public static final Guest AISHA_ADEBAYO = Guest.builder()
            .fullName("Aisha Adebayo")
            .email("aisha.adebayo@example.com")
            .nationalId("NG987654321")
            .build();

    public static final Guest EMEKA_OKAFOR = Guest.builder()
            .fullName("Emeka Okafor")
            .email("emeka.okafor@example.com")
            .nationalId("NG456789123")
            .build();

    public static final List<Guest> ALL_GUESTS = Arrays.asList(
            JOAO_SILVA,
            MARIA_SANTOS,
            CARLOS_OLIVEIRA,
            OLIVER_JOHNSON,
            CHARLOTTE_WILLIAMS,
            MIKA_HAKKINEN,
            EMMA_KORHONEN,
            HANS_MULLER,
            SOPHIE_SCHMIDT,
            LUKAS_FISCHER,
            CARLOS_MAMANI,
            MARIA_QUISPE,
            MICHAEL_SMITH,
            JENNIFER_DAVIS,
            ROBERT_WILSON,
            MOHAMED_ALI,
            FATIMA_HASSAN,
            ANTONIO_GARCIA,
            ISABEL_RODRIGUEZ,
            JAVIER_LOPEZ,
            WEI_ZHANG,
            LI_WANG,
            MEI_CHEN,
            AHMED_IBRAHIM,
            AISHA_MOHAMED,
            PEDRO_SANTOS,
            ANA_SILVA,
            JUAN_HERNANDEZ,
            SOFIA_GONZALEZ,
            DIEGO_RODRIGUEZ,
            ALI_KHAN,
            SAIMA_AHMED,
            JACK_THOMPSON,
            OLIVIA_WILSON,
            NOAH_BROWN,
            PIERRE_DUBOIS,
            MARIE_LAURENT,
            THOMAS_MOREAU,
            RAJ_PATEL,
            PRIYA_SHARMA,
            AMIT_KUMAR,
            KHALID_AL_MOHAMMED,
            NOURA_AL_SABAH,
            THABO_MBEKI,
            NOMSA_ZULU,
            SIPHO_NDLOVU,
            YUKI_TANAKA,
            HIROSHI_SATO,
            AIKO_YAMAMOTO,
            ABDULLAH_AL_SAUD,
            SARAH_AL_RASHID,
            MINH_NGUYEN,
            LAN_TRAN,
            HUY_PHAM,
            MIN_HO_KIM,
            JI_EUN_PARK,
            SEUNG_HYUN_LEE,
            CARLOS_RAMIREZ,
            CAMILA_RODRIGUEZ,
            LIAM_TREMBLAY,
            EMMA_MARTIN,
            NOAH_GAGNON,
            DIEGO_GONZALEZ,
            VALENTINA_LOPEZ,
            JUAN_PEREZ,
            CHINEDU_OKEKE,
            AISHA_ADEBAYO,
            EMEKA_OKAFOR
    );
}