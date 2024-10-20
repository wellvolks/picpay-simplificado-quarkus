package picpay.simplificado.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import picpay.simplificado.entities.transaction.Transaction;
@ApplicationScoped
public class TransacationRepository implements PanacheRepository<Transaction> {
}
