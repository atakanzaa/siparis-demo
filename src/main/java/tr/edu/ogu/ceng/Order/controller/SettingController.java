package tr.edu.ogu.ceng.Order.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import tr.edu.ogu.ceng.Order.Service.SettingService;

@RequestMapping("/api/v1/setting")
@RestController
@RequiredArgsConstructor
public class SettingController {
    private final SettingService settingService;

    @GetMapping("/{id}")
    public Object getSetting(@PathVariable Long id) {
        return settingService.getSetting(id);
    }

    RestClient restClient = RestClient.create();
}
