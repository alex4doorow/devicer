/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.afa.devicer.web.controllers.system;

import com.afa.devicer.web.controllers.BaseWebController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/web")
class WelcomeController extends BaseWebController {

	@GetMapping("/")
	public String home(Model model) {
		log.info("[START] {}", "home page");
		populateDefaultModel(model);
		return "index";
	}

	@GetMapping("/errors")
	public String errors(Model model) {
		log.info("[START] {}", "ERRORS");
		populateDefaultModel(model);
		return "error";
	}

}
