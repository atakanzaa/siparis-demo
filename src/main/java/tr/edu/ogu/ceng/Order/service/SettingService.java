package tr.edu.ogu.ceng.Order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tr.edu.ogu.ceng.Order.repository.SettingRepository;

@Service
@RequiredArgsConstructor

public class SettingService {

    private final SettingRepository settingRepository;

    public  Object getSetting(Long id) {
        return settingRepository.getReferenceById(id);
    }

}
