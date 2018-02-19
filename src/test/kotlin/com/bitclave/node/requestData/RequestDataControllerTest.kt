package com.bitclave.node.requestData

import com.bitclave.node.extensions.toJsonString
import com.bitclave.node.repository.models.RequestData
import com.bitclave.node.repository.models.SignedRequest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpHeaders
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ActiveProfiles("test")
@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class RequestDataControllerTest {

    protected val from = "02710f15e674fbbb328272ea7de191715275c7a814a6d18a59dd41f3ef4535d9ea"
    protected val to = "12710f15e674fbbb328272ea7de191715275c7a814a6d18a59dd41f3ef4535d9ea"

    @Autowired
    private lateinit var mvc: MockMvc

    protected lateinit var requestDataRequest: SignedRequest<RequestData>
    protected lateinit var requestDataResponse: SignedRequest<String>
    private var httpHeaders: HttpHeaders = HttpHeaders()

    @Before fun setup() {
        requestDataRequest = SignedRequest(RequestData(), from)
        requestDataResponse = SignedRequest("", from)

        httpHeaders.set("Accept", "application/json")
        httpHeaders.set("Content-Type", "application/json")
    }

    @Test fun `get request by state`() {
        this.mvc.perform(get("/request/from/$from/state/${RequestData.RequestDataState.AWAIT}/")
                .headers(httpHeaders))
                .andExpect(status().isOk)

        this.mvc.perform(get("/request/from/$from/to/$to/state/${RequestData.RequestDataState.AWAIT}/")
                .headers(httpHeaders))
                .andExpect(status().isOk)

        this.mvc.perform(get("/request/to/$to/state/${RequestData.RequestDataState.AWAIT}/")
                .headers(httpHeaders))
                .andExpect(status().isOk)
    }

    @Test fun `create request for client`() {
        this.mvc.perform(post("/request/")
                .content(requestDataRequest.toJsonString())
                .headers(httpHeaders))
                .andExpect(status().isOk)
    }

    @Test fun `create response for client`() {
        this.mvc.perform(patch("/request/1/")
                .content(requestDataResponse.toJsonString())
                .headers(httpHeaders))
                .andExpect(status().isOk)
    }

}