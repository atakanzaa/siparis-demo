package tr.edu.ogu.ceng.Order.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.edu.ogu.ceng.Order.service.SettingService;

@RequestMapping("/api/v1/setting")
@RestController
@RequiredArgsConstructor
public class SettingController {
    private final SettingService settingService;

    @GetMapping
    public String getSetting() {
        return "atakanzaa";
    }
}
