package tr.edu.ogu.ceng.Order.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import tr.edu.ogu.ceng.Order.repository.SettingRepository;

@Service
@RequiredArgsConstructor

public class SettingService {

    private final SettingRepository settingRepository;

    public  Object getSetting(Long id) {
        return settingRepository.getReferenceById(id);
    }

}
