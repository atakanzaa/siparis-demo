package tr.edu.ogu.ceng.Order.controller;



import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.edu.ogu.ceng.Order.service.SettingService;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/setting")

public class SettingController {
    // @GetMapping
   // public String getSetting() {
     //   return "BATUHAN";}

    private final SettingService settingService;

    @GetMapping("/{id}")
    public Object getSetting(@PathVariable Long id) {
        return settingService.getSetting(id);
    }
}
