package id.ac.prisma.siapmobilebackend.data.repo;

import id.ac.prisma.siapmobilebackend.data.model.TbUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TbUserRepository extends CrudRepository<TbUser, Integer> {
    Optional<TbUser> findByEmail(String email);
}
