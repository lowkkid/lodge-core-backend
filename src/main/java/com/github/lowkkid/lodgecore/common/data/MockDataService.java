package com.github.lowkkid.lodgecore.common.data;

import com.github.lowkkid.lodgecore.booking.domain.entity.Booking;
import com.github.lowkkid.lodgecore.booking.domain.repository.BookingRepository;
import com.github.lowkkid.lodgecore.cabin.domain.entity.Cabin;
import com.github.lowkkid.lodgecore.cabin.domain.repository.CabinRepository;
import com.github.lowkkid.lodgecore.common.data.provider.MockBookingsProvider;
import com.github.lowkkid.lodgecore.common.data.provider.MockCabinsProvider;
import com.github.lowkkid.lodgecore.common.data.provider.MockCountriesProvider;
import com.github.lowkkid.lodgecore.common.data.provider.MockGuestsProvider;
import com.github.lowkkid.lodgecore.common.data.provider.MockSettingsProvider;
import com.github.lowkkid.lodgecore.common.data.provider.MockUsersProvider;
import com.github.lowkkid.lodgecore.guest.domain.entity.Country;
import com.github.lowkkid.lodgecore.guest.domain.entity.Guest;
import com.github.lowkkid.lodgecore.guest.domain.repository.CountryRepository;
import com.github.lowkkid.lodgecore.guest.domain.repository.GuestRepository;
import com.github.lowkkid.lodgecore.setting.domain.repository.SettingRepository;
import com.github.lowkkid.lodgecore.user.domain.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
public class MockDataService {

    private final CabinRepository cabinRepository;
    private final SettingRepository settingRepository;
    private final CountryRepository countryRepository;
    private final GuestRepository guestRepository;
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @PersistenceContext
    private final EntityManager entityManager;

    private static final Map<Integer, Integer> GUEST_COUNTRY_MAPPING = createGuestToCountryMapping();
    private static final Map<Integer, Integer> BOOKING_CABIN_MAPPING = createBookingCabinMapping();
    private static final Map<Integer, Integer> BOOKING_GUEST_MAPPING = createBookingGuestMapping();

    private static Map<Integer, Integer> createGuestToCountryMapping() {
        Map<Integer, Integer> mapping = new HashMap<>();

        mapping.put(1, 1);
        mapping.put(2, 1);
        mapping.put(3, 1);

        mapping.put(4, 2);
        mapping.put(5, 2);

        mapping.put(6, 3);
        mapping.put(7, 3);

        mapping.put(8, 4);
        mapping.put(9, 4);
        mapping.put(10, 4);

        mapping.put(11, 5);
        mapping.put(12, 5);

        mapping.put(13, 6);
        mapping.put(14, 6);
        mapping.put(15, 6);

        mapping.put(16, 7);
        mapping.put(17, 7);

        mapping.put(18, 8);
        mapping.put(19, 8);
        mapping.put(20, 8);

        mapping.put(21, 9);
        mapping.put(22, 9);
        mapping.put(23, 9);

        mapping.put(24, 10);
        mapping.put(25, 10);

        mapping.put(26, 11);
        mapping.put(27, 11);

        mapping.put(28, 12);
        mapping.put(29, 12);
        mapping.put(30, 12);

        mapping.put(31, 13);
        mapping.put(32, 13);

        mapping.put(33, 14);
        mapping.put(34, 14);
        mapping.put(35, 14);

        mapping.put(36, 15);
        mapping.put(37, 15);
        mapping.put(38, 15);

        mapping.put(39, 16);
        mapping.put(40, 16);
        mapping.put(41, 16);

        mapping.put(42, 17);
        mapping.put(43, 17);

        mapping.put(44, 18);
        mapping.put(45, 18);
        mapping.put(46, 18);

        mapping.put(47, 19);
        mapping.put(48, 19);
        mapping.put(49, 19);

        mapping.put(50, 20);
        mapping.put(51, 20);

        mapping.put(52, 21);
        mapping.put(53, 21);
        mapping.put(54, 21);


        mapping.put(55, 22);
        mapping.put(56, 22);
        mapping.put(57, 22);

        mapping.put(58, 23);
        mapping.put(59, 23);

        mapping.put(60, 24);
        mapping.put(61, 24);
        mapping.put(62, 24);

        mapping.put(63, 25);
        mapping.put(64, 25);
        mapping.put(65, 25);

        mapping.put(66, 26);
        mapping.put(67, 26);
        mapping.put(68, 26);

        return mapping;
    }

    private static Map<Integer, Integer> createBookingCabinMapping() {
        Map<Integer, Integer> mapping = new HashMap<>();

        mapping.put(1, 1);
        mapping.put(2, 1);
        mapping.put(3, 1);
        mapping.put(4, 2);
        mapping.put(5, 2);
        mapping.put(6, 2);
        mapping.put(7, 3);
        mapping.put(8, 3);
        mapping.put(9, 3);
        mapping.put(10, 4);
        mapping.put(11, 4);
        mapping.put(12, 4);
        mapping.put(13, 5);
        mapping.put(14, 5);
        mapping.put(15, 5);
        mapping.put(16, 6);
        mapping.put(17, 6);
        mapping.put(18, 6);
        mapping.put(19, 7);
        mapping.put(20, 7);
        mapping.put(21, 7);
        mapping.put(22, 8);
        mapping.put(23, 8);
        mapping.put(24, 8);
        mapping.put(25, 2);
        mapping.put(26, 2);
        mapping.put(27, 3);
        mapping.put(28, 4);
        mapping.put(29, 1);
        mapping.put(30, 1);

        return mapping;
    }

    private static Map<Integer, Integer> createBookingGuestMapping() {
        Map<Integer, Integer> mapping = new HashMap<>();

        mapping.put(1, 2);
        mapping.put(2, 3);
        mapping.put(3, 4);
        mapping.put(4, 5);
        mapping.put(5, 6);
        mapping.put(6, 7);
        mapping.put(7, 8);
        mapping.put(8, 9);
        mapping.put(9, 10);
        mapping.put(10, 11);
        mapping.put(11, 12);
        mapping.put(12, 13);
        mapping.put(13, 14);
        mapping.put(14, 15);
        mapping.put(15, 16);
        mapping.put(16, 17);
        mapping.put(17, 18);
        mapping.put(18, 19);
        mapping.put(19, 20);
        mapping.put(20, 21);
        mapping.put(21, 22);
        mapping.put(22, 1);
        mapping.put(23, 23);
        mapping.put(24, 24);
        mapping.put(25, 25);
        mapping.put(26, 26);
        mapping.put(27, 27);
        mapping.put(28, 28);
        mapping.put(29, 29);
        mapping.put(30, 30);

        return mapping;
    }

    @Transactional
    public void loadData() {
        clearData();
        resetAllSequences();
        loadUsers();
        loadCountries();
        loadCabins();
        loadSettings();
        loadGuestsWithCountries();
        loadBookingsWithGuestsAndCabins();
    }

    private void clearData() {
        userRepository.deleteAllInBatch();
        settingRepository.deleteAllInBatch();
        bookingRepository.deleteAllInBatch();
        cabinRepository.deleteAllInBatch();
        guestRepository.deleteAllInBatch();
        countryRepository.deleteAllInBatch();
    }

    private void resetAllSequences() {
        entityManager.createNativeQuery("""
                DO $$
                DECLARE
                    seq_name text;
                BEGIN
                    FOR seq_name IN\s
                        SELECT sequence_name
                        FROM information_schema.sequences\s
                        WHERE sequence_schema = 'public'
                    LOOP
                        EXECUTE 'ALTER SEQUENCE ' || seq_name || ' RESTART WITH 1';
                    END LOOP;
                END $$;
                """).executeUpdate();
    }

    private void loadUsers() {
        var admin = MockUsersProvider.ADMIN;
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        userRepository.save(admin);

        var employee = MockUsersProvider.EMPLOYEE;
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        userRepository.save(employee);
    }

    private void loadCountries() {
        countryRepository.saveAll(MockCountriesProvider.ALL_COUNTRIES);
    }

    private void loadCabins() {
        cabinRepository.saveAll(MockCabinsProvider.ALL_CABINS);
    }

    private void loadSettings() {
        settingRepository.save(MockSettingsProvider.SETTING);
    }

    private void loadGuestsWithCountries() {
        Map<Long, Country> countriesById = new HashMap<>();
        countryRepository.findAll().forEach(country ->
                countriesById.put(country.getId(), country)
        );


        int guestIndex = 1;

        for (Guest guest : MockGuestsProvider.ALL_GUESTS) {
            Integer countryId = GUEST_COUNTRY_MAPPING.get(guestIndex++);

            if (countryId != null) {
                var country = countriesById.get(countryId.longValue());

                if (country != null) {
                    guest.setCountry(country);
                }
            }
        }

        guestRepository.saveAll(MockGuestsProvider.ALL_GUESTS);
    }

    private void loadBookingsWithGuestsAndCabins() {
        Map<Long, Cabin> cabinsById = new HashMap<>();
        cabinRepository.findAll().forEach(cabin -> cabinsById.put(cabin.getId(), cabin));

        Map<Long, Guest> guestsById = new HashMap<>();
        guestRepository.findAll().forEach(guest -> guestsById.put(guest.getId(), guest));

        int bookingIndex = 1;

        for (Booking booking : MockBookingsProvider.ALL_BOOKINGS) {
            Integer cabinId = BOOKING_CABIN_MAPPING.get(bookingIndex);
            Integer guestId = BOOKING_GUEST_MAPPING.get(bookingIndex++);

            if (cabinId != null && guestId != null) {
                Cabin cabin = cabinsById.get(cabinId.longValue());
                Guest guest = guestsById.get(guestId.longValue());

                if (cabin != null && guest != null) {
                    booking.setCabin(cabin);
                    booking.setGuest(guest);
                }
            }
        }

        bookingRepository.saveAll(MockBookingsProvider.ALL_BOOKINGS);
    }

}
