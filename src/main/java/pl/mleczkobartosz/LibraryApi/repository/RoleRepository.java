package pl.mleczkobartosz.LibraryApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mleczkobartosz.LibraryApi.Entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    public Role findByName(String role);

}
