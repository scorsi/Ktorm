package me.liuwj.ktorm.entity

import me.liuwj.ktorm.BaseTest
import me.liuwj.ktorm.dsl.eq
import me.liuwj.ktorm.dsl.isNull
import org.junit.Test

/**
 * Created by vince on Mar 22, 2019.
 */
class EntitySequenceTest : BaseTest() {

    @Test
    fun testRealSequence() {
        val sequence = listOf(1, 2, 3).asSequence()
        sequence.filter { it > 0 }
    }

    @Test
    fun testToList() {
        val employees = Employees.asSequence().toList()
        assert(employees.size == 4)
        assert(employees[0].name == "vince")
        assert(employees[0].department.name == "tech")
    }

    @Test
    fun testMap() {
        val names = Employees.asSequence().map { it.name }.map { it.toUpperCase() }.toList()
        assert(names.size == 4)
        assert(names[0] == "VINCE")
        assert(names[1] == "MARRY")
    }

    @Test
    fun testMapIndexed() {
        val names = Employees.asSequence().map { it.name }.mapIndexed { i, name -> "$i.$name" }.toList()
        assert(names.size == 4)
        assert(names[0] == "0.vince")
        assert(names[1] == "1.marry")
    }

    @Test
    fun testMapTo() {
        val names = Employees.asSequence().map { it.name }.mapTo(ArrayList()) { it.toUpperCase() }
        assert(names.size == 4)
        assert(names[0] == "VINCE")
        assert(names[1] == "MARRY")
    }

    @Test
    fun testMapIndexedTo() {
        val names = Employees.asSequence().map { it.name }.mapIndexedTo(ArrayList()) { i, name -> "$i.$name" }
        assert(names.size == 4)
        assert(names[0] == "0.vince")
        assert(names[1] == "1.marry")
    }

    @Test
    fun testFilter() {
        val names = Employees
            .asSequence()
            .filter { it.departmentId eq 1 }
            .filterNot { it.managerId.isNull() }
            .map { it.name }
            .toList()

        assert(names.size == 1)
        assert(names[0] == "marry")
    }

    @Test
    fun testFilterTo() {
        val names = Employees
            .asSequence()
            .map { it.name }
            .filter { it.departmentId eq 1 }
            .filterTo(ArrayList()) { it.managerId.isNull() }

        assert(names.size == 1)
        assert(names[0] == "vince")
    }
}