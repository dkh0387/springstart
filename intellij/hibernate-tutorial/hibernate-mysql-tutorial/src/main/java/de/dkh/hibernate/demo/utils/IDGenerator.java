package de.dkh.hibernate.demo.utils;

import de.dkh.hibernate.demo.entity.Student;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Random;

/**
 * We can actually create an own implementation of generation strategy.
 * We just need to override the {@linkplain IdentifierGenerator#generate(SharedSessionContractImplementor, Object)}.
 * Usage: see {@linkplain Student#getCustomId()}.
 */
public class IDGenerator implements IdentifierGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        String prefix = "Custom_String";
        Calendar calendar = Calendar.getInstance();

        if (object instanceof Student) {
            Student student = (Student) object;
            return student.getId() + "_" + prefix + "_" + generateCustomId() + "_" + calendar.get(Calendar.YEAR);
        }
        return prefix + "_" + generateCustomId() + "_" + calendar.get(Calendar.YEAR);
    }

    public int generateCustomId() {
        Random random = new Random();
        return random.nextInt(100);
    }
}
