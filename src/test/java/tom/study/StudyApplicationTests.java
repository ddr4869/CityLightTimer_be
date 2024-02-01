package tom.study;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import tom.study.domain.customer.model.entity.Customer;
import tom.study.domain.customer.repository.CustomerRepository;
import tom.study.domain.customer.service.CustomerService;
import tom.study.domain.reservation.model.entity.Reservation;
import tom.study.domain.reservation.repository.ReservationRepository;
import tom.study.domain.reservation.service.ReservationService;

import java.util.List;
import java.util.Optional;


@SpringBootTest
@Slf4j
class StudyApplicationTests {

	@Autowired
	ReservationService reservationService;
//	@Autowired
//	CustomerService customerService;
	@Autowired
	ReservationRepository reservationRepository;
	@Autowired
	CustomerRepository customerRepository;

	@Test
	void createReservationsTest() {
		Reservation reservation = new Reservation();
		for(int i=0;i<20;i++) {
			reservation.setReservationPaymentFilm("TEST"+String.valueOf(i));
			reservation.setReservationPaymentScheduleId((long) (1000+i));
			if (i%4==0) {
				reservation.setCustomerId("tom");
			} else {
				reservation.setCustomerId("CustomerID"+String.valueOf(i));
			}
			reservation.setReservationPaymentId((long) (i));
			reservationService.createReservation(reservation);
		}
	}

	@Test
	void createOneReservationsTest() {
		Reservation reservation = new Reservation();
		reservation.setReservationPaymentScheduleId(1000L);
		reservation.setCustomerId("tom");
		reservation.setReservationPaymentId(1000L);
		reservation.setReservationPaymentFilm("톰과제리");
		reservationService.createReservation(reservation);

	}

	@Test
	void queryAllReservation() {
		List<Reservation> reservations = reservationService.queryAllReservations();
		for(Reservation r: reservations) {
			log.info("{}, {}, {}, {}",r.getReservationPaymentId(), r.getCustomerId(),
					r.getReservationPaymentScheduleId(), r.getReservationPaymentFilm());
		}
	}

	@Test
	void queryPageReservationTest() {
		Pageable pageable = PageRequest.of(0,5);
		Page<Reservation>  reservations = reservationRepository.findAll(pageable);

		for(Reservation r: reservations) {
			log.info("{}, {}, {}, {}",r.getReservationPaymentId(), r.getCustomerId(),
					r.getReservationPaymentScheduleId(), r.getReservationPaymentFilm());
		}
	}

	@Test
	void querySpecialDataTest() {
		List<Reservation> reservations = reservationRepository.findByCustomerId("CustomerID16");
		for(Reservation r: reservations) {
			log.info("{}, {}, {}, {}",r.getReservationPaymentId(), r.getCustomerId(),
					r.getReservationPaymentScheduleId(), r.getReservationPaymentFilm());
		}
	}

	@Test
	void queryReservationCustomerTest3() {
		Customer customer = customerRepository.findByCustomerName("dustin").orElseThrow(() -> new IllegalArgumentException("test"));
		Customer mockCustomer = new Customer();
		Customer orElseCustomer = new Customer();
		mockCustomer.setCustomerName("낄낄커스토머");
		orElseCustomer = Optional.ofNullable(customer).orElse(mockCustomer);
		log.info("{}",orElseCustomer.getCustomerName());
	}

	@Test
	void queryFirst2CustomerTest() {
		//Pageable pageable = PageRequest.of(0,5);
		List<Reservation> reservations = reservationRepository.findFirst2ByCustomerId("tom");
		for(Reservation r: reservations) {
			log.info("{}, {}, {}, {}",r.getReservationPaymentId(), r.getCustomerId(),
					r.getReservationPaymentScheduleId(), r.getReservationPaymentFilm());
		}
	}
}
