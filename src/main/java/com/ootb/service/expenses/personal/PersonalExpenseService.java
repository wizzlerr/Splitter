package com.ootb.service.expenses.personal;

import com.ootb.db.expenses.personal.dao.PersonalExpenseDao;
import com.ootb.db.expenses.personal.factory.PersonalExpenseFactory;
import com.ootb.service.expenses.personal.type.PersonalExpense;
import com.ootb.service.infotainment.notification.NotificationService;
import com.ootb.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Adam on 2017-03-26.
 */
@Service
public class PersonalExpenseService {

    @Autowired
    private UserService userService;

    @Autowired
    private PersonalExpenseDao personalExpenseDao;

    @Autowired
    private PersonalExpenseFactory personalExpenseFactory;

    @Autowired
    private NotificationService notificationService;

    public void registerExpense(PersonalExpense expense) {
        personalExpenseDao.savePersonalExpense(personalExpenseFactory.getPersonalExpense(expense, userService.getLoggedUser()));
        notificationService.addInfoMessage("Dodano wydatek");
    }

    public List<PersonalExpense> getExpenses() {
        return personalExpenseFactory.getPersonalExpense(personalExpenseDao.findByUser(userService.getLoggedUser()));
    }

    public void deleteExpense(Long id) {
        if(personalExpenseDao.deleteById(id) == null) {
            notificationService.addWarningMessage("Brak wydatku");
        }
        notificationService.addInfoMessage("Usunięto wydatek");
    }

    public PersonalExpense getExpense(Long id) {
        return personalExpenseFactory.getPersonalExpense(personalExpenseDao.findById(id));
    }

    public void updateExpense(PersonalExpense expense) {
        com.ootb.db.expenses.personal.type.PersonalExpense dbExpense = personalExpenseDao.findById(expense.getId());
        dbExpense.setExpense(expense.getExpense());
        dbExpense.setDescription(expense.getDescription());
        dbExpense.setName(expense.getName());
        dbExpense.setCategory(expense.getCategory().name());
        dbExpense.setCurrency(expense.getCurrency());
        dbExpense.setDate(expense.getDate().toString());
        personalExpenseDao.update(dbExpense);
    }
}
