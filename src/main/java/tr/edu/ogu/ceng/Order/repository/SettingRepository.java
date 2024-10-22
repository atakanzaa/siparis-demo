package tr.edu.ogu.ceng.Order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.edu.ogu.ceng.Order.entity.Setting;


public interface SettingRepository extends JpaRepository<Setting,Long> {

}