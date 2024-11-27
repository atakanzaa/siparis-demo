package tr.edu.ogu.ceng.Order.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import tr.edu.ogu.ceng.Order.entity.Setting;
import tr.edu.ogu.ceng.Order.repository.SettingRepository;

@Service
@RequiredArgsConstructor

public class SettingService {

    private final SettingRepository settingRepository;

    public  Object getSetting(Long id) {
        return settingRepository.getReferenceById(id);
    }
    public Setting createSetting(String key, String value) {
        Setting setting = new Setting();
        setting.setKey(key);
        setting.setValue(value);
        return settingRepository.save(setting);
    }
    public Setting updateSetting(Long id, String key, String value) {
        Setting setting = settingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Setting not found"));
        setting.setKey(key);
        setting.setValue(value);
        return settingRepository.save(setting);
    }
    public void deleteSetting(Long id) {
        settingRepository.deleteById(id);
    }

}
