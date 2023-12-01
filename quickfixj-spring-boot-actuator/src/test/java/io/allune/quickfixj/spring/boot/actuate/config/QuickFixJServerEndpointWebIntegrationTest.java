/*
 * Copyright 2017-2023 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.allune.quickfixj.spring.boot.actuate.config;

import io.allune.quickfixj.spring.boot.actuate.endpoint.test.WebEndpointTest;
import io.allune.quickfixj.spring.boot.starter.EnableQuickFixJServer;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;

public class QuickFixJServerEndpointWebIntegrationTest extends AbstractQuickFixJBaseEndpointAutoConfiguration {

	public QuickFixJServerEndpointWebIntegrationTest() {
	}

	@WebEndpointTest
	public void shouldReadProperties(WebTestClient client) {
		client.get()
			.uri((builder) -> builder.path("/actuator/quickfixjserver").build())
			.exchange()
			.expectStatus()
			.isOk()
			.expectBody()
			.consumeWith(assertSessionProperties());
	}

	@Configuration
	@EnableAutoConfiguration
	@EnableQuickFixJServer
	@PropertySource("classpath:application-server.properties")
	public static class QuickFixJServerEndpointTestConfig {
	}
}
