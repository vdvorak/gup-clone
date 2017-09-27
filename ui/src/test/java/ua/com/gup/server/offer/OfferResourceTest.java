package ua.com.gup.server.offer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ua.com.gup.domain.filter.OfferFilter;
import ua.com.gup.dto.offer.view.OfferViewShortDTO;
import ua.com.gup.service.offer.OfferService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml", "classpath:dispatcherServlet.xml"})
@WebAppConfiguration
public class OfferResourceTest {

    private MockMvc mockMvc;

    @Mock
    private OfferService offerService;
    @InjectMocks
    private OfferResource offerResource;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(offerResource)
                .build();
    }


    @Test
    public void getAllOffersByFilterTestMaxSize() throws Exception {

        Page<OfferViewShortDTO> dtoPage = new PageImpl<OfferViewShortDTO>();
        OfferFilter offerFilter = new OfferFilter();
        int page = 0;
        int size = 600;
        PageRequest pageable = new PageRequest(page, size);

        when(offerService.findAll(offerFilter, pageable)).thenReturn(dtoPage);
    }

}