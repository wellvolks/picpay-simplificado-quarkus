package picpay.simplificado.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import picpay.simplificado.entities.user.User;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

}
