package br.com.danielteles.chatproject.service;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RoomServiceTest {

//	@Autowired
//	private ArangoTemplate template;
//	
//	@Autowired
//	private RoomRepository repository;
//	
//	@BeforeEach
//	public void configure() {
//		template.collection("rooms-test");
//	} 
//	
//	@AfterEach
//	public void clean() {
//		template.collection("rooms-test").drop();
//	} 
//	
//	@Test
//	public void create() {
//		Room mock = Room.builder().id("test01").name("sala-01").capacity(RoomCapacity.SMALL).build();
//		var service = new RoomService(repository);
//		service.create(mock);
//		var result = template.find(mock.getId(), Room.class).orElse(null);
//		
//		assertNotNull(result);
//		assertEquals(mock.getId(), result.getId());
//		assertEquals(mock.getName(), result.getName());
//		assertEquals(mock.getCapacity(), result.getCapacity());
//	}
}
