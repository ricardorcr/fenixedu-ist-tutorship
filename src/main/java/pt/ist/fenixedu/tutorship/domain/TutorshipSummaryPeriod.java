/**
 * Copyright © 2011 Instituto Superior Técnico
 *
 * This file is part of FenixEdu Tutorship.
 *
 * FenixEdu Tutorship is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FenixEdu Tutorship is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FenixEdu Tutorship.  If not, see <http://www.gnu.org/licenses/>.
 */
package pt.ist.fenixedu.tutorship.domain;

import org.fenixedu.academic.domain.ExecutionSemester;
import org.fenixedu.bennu.core.domain.Bennu;
import org.joda.time.Interval;
import org.joda.time.LocalDate;

import pt.ist.fenixedu.tutorship.ui.Action.pedagogicalCouncil.TutorshipSummaryPeriodBean;
import pt.ist.fenixframework.Atomic;

public class TutorshipSummaryPeriod extends TutorshipSummaryPeriod_Base {

    public TutorshipSummaryPeriod(ExecutionSemester executionSemester, LocalDate beginDate, LocalDate endDate) {
        super();
        setRootDomainObject(Bennu.getInstance());
        setExecutionSemester(executionSemester);
        setBeginDate(beginDate);
        setEndDate(endDate);
    }

    @Atomic
    public void update(TutorshipSummaryPeriodBean bean) {
        setBeginDate(bean.getBeginDate());
        setEndDate(bean.getEndDate());
    }

    @Atomic
    public static TutorshipSummaryPeriod create(TutorshipSummaryPeriodBean bean) {
        TutorshipSummaryPeriod tutorshipSummaryPeriod =
                new TutorshipSummaryPeriod(bean.getExecutionSemester(), bean.getBeginDate(), bean.getEndDate());

        return tutorshipSummaryPeriod;
    }

    public boolean isOpenNow() {
        Interval interval =
                new Interval(getBeginDate().toDateTimeAtStartOfDay(), getEndDate().plusDays(1).toDateTimeAtStartOfDay());
        return interval.containsNow();
    }

}
