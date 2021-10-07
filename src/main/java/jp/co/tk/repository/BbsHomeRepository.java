package jp.co.tk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jp.co.tk.entity.BbsHomeEntity;

@Repository
public interface BbsHomeRepository extends JpaRepository<BbsHomeEntity, Long>{



	@Query(value = "select * from bbs"
			, nativeQuery = true)
	public List<BbsHomeEntity> findBySelectAll();

}
