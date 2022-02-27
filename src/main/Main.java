package main;

import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

import models.Employee;

public class Main {

	Random rand = new Random();
	Scanner scan = new Scanner(System.in);
	
	int manager = 0;
	int supervisor = 0;
	int admin = 0;
	
	Vector<Employee> employee = new Vector<>();
	Vector<String> tempEmployeeId = new Vector<>();
	
	void cls() {
		for (int i = 0; i < 30; i++) {
			System.out.println();
		}
	}
	
	String createEmployeeId() {
		String employeeId = "";
		boolean isUnique;
		do {
			String alphabet = "" + (char) (rand.nextInt(26) + 'A') + (char) (rand.nextInt(26) + 'A');
			String num = "";
			isUnique = true;
			
			for (int i = 0; i < 4; i++) {
				num = num + rand.nextInt(10);
			}
			employeeId = alphabet + "-" + num;
			for (Employee employee2 : employee) {
				if (employee2.getEmployeeId().equals(employeeId)) {
					isUnique = false;
				}
			}
		} while(!isUnique);
		return employeeId;
	}
	
	int calculateSalary(String position) {
		int salary = 0;
		
		if (position.equals("Manager")) {
			salary = 8000000;
			manager++;
			if (manager > 3) {				
				for (Employee employee2 : employee) {
					if (employee2.getPosition().equals("Manager")) {
						tempEmployeeId.add(employee2.getEmployeeId());
						float tempSalary = (float) (employee2.getSalary()*0.1);
						employee2.setSalary(employee2.getSalary() + (int)tempSalary);
					}
				}
			}
		} else if (position.equals("Supervisor")) {
			salary = 6000000;
			supervisor++;
			if (supervisor > 3) {				
				for (Employee employee2 : employee) {
					if (employee2.getPosition().equals("Supervisor")) {
						tempEmployeeId.add(employee2.getEmployeeId());
						float tempSalary = (float) (employee2.getSalary()*0.075);
						employee2.setSalary(employee2.getSalary() + (int)tempSalary);
					}
				}
			}
		} else if (position.equals("Admin")) {
			salary = 4000000;
			admin++;
			if (admin > 3) {				
				for (Employee employee2 : employee) {
					if (employee2.getPosition().equals("Admin")) {
						tempEmployeeId.add(employee2.getEmployeeId());
						float tempSalary = (float) (employee2.getSalary()*0.05);
						employee2.setSalary(employee2.getSalary() + (int)tempSalary);
					}
				}
			}
		}
		return salary;
	}
	
	void createEmployee() {
		String employeeCode = createEmployeeId();
		String name = "";
		String gender = "";
		String position = "";
		int salary = 0;
		
		do {
			System.out.print("Input nama karyawan [>=3]: ");
			name = scan.nextLine();
		} while(name.length() < 3);
		
		do {
			System.out.print("Input jenis kelamin [Laki-laki | Perempuan] (Case Sensitive): ");
			gender = scan.nextLine();
		} while(!gender.equals("Laki-laki") && !gender.equals("Perempuan"));
		
		do {
			System.out.print("Input jabatan [Manager | Supervisor | Admin] (Case Sensitive): ");
			position = scan.nextLine();
		} while(!position.equals("Manager") && !position.equals("Supervisor") && !position.equals("Admin"));
		
		salary = calculateSalary(position);
		Employee newEmployee = new Employee(employeeCode, name, gender, position, salary);
		employee.add(newEmployee);
		System.out.println("Berhasil menambahkan karyawan dengan id " + employeeCode);
		
		if (position.equals("Manager") && manager > 3) {			
			System.out.print("Bonus sebesar 10% telah diberikan kepada karyawan dengan id ");
			for (int i = 0; i < tempEmployeeId.size(); i++) {
				System.out.print(tempEmployeeId.get(i) + " ");
			}
			System.out.println();
			tempEmployeeId.clear();
		} else if (position.equals("Supervisor") && supervisor > 3) {
			System.out.print("Bonus sebesar 7.5% telah diberikan kepada karyawan dengan id ");
			for (int i = 0; i < tempEmployeeId.size(); i++) {
				System.out.print(tempEmployeeId.get(i) + " ");
			}
			System.out.println();
			tempEmployeeId.clear();
		} else if (position.equals("Admin") && admin > 3) {
			System.out.print("Bonus sebesar 5% telah diberikan kepada karyawan dengan id ");
			for (int i = 0; i < tempEmployeeId.size(); i++) {
				System.out.print(tempEmployeeId.get(i) + " ");
			}
			System.out.println();
			tempEmployeeId.clear();
		}
		System.out.println("ENTER to return");
		scan.nextLine();
	}
	
	void sort(int left, int right) {
		if (left >= right) {
			return;
		}
		
		int mid = (left + right)/2;
			
		sort(left, mid);
		sort(mid+1, right);
		
		merge(left, mid, right);
	}
	
	void merge(int left, int mid, int right) {
		Vector <Employee> temp = new Vector<>();
		
		int i = left, j = mid+1;
		
		while(i <= mid && j <= right) {

			if (employee.get(i).getName().compareTo(employee.get(j).getName()) < 0) {
				temp.add(employee.get(i));
				i++;
			} else {
				temp.add(employee.get(j));
				j++;
			}
		}
		
		while(i <= mid) {
			temp.add(employee.get(i));
			i++;
		}
		
		while (j <= right) {
			temp.add(employee.get(j));
			j++;
		}
		
		for (int k = left; k <= right; k++) {
			employee.set(k, temp.get(k-left));
		}
	}
	
	void viewEmployee() {
		int i = 1;
		sort(0, employee.size()-1);
		System.out.println("|----|-----------------|------------------------------|---------------|---------------|---------------|");
		System.out.println("|No  |Kode Karyawan    |Nama Karyawan                 |Jenis Kelamin  |Posisi         |Salary         |");
		System.out.println("|----|-----------------|------------------------------|---------------|---------------|---------------|");
		for (Employee employee2 : employee) {
			System.out.printf("|%4d|%17s|%30s|%15s|%15s|%15d|\n", i, employee2.getEmployeeId(), employee2.getName(), employee2.getGender(), employee2.getPosition(), employee2.getSalary());
			i++;
		}
		System.out.println("|----|-----------------|------------------------------|---------------|---------------|---------------|");
	}
	
	public Main() {
		int in = 0;
		while(in != 5) {
			cls();
			System.out.println("Menu:");
			System.out.println("1. Insert Data Karyawan");
			System.out.println("2. View Data Karyawan");
			System.out.println("3. Update Data Karyawan");
			System.out.println("4. Delete Data Karyawan");
			System.out.println("5. Exit");
			System.out.print(">> ");
			in = scan.nextInt();
			scan.nextLine();
			
			switch(in) {
				case 1: {
					cls();
					createEmployee();
					break;
				}
				case 2: {
					cls();
					viewEmployee();
					System.out.println();
					System.out.println("ENTER to return");
					scan.nextLine();
					break;
				}
				case 3: {
					cls();
					viewEmployee();
					if (employee.size() == 0) {
						System.out.println("Tidak ada karyawan yang bisa diupdate");
						System.out.println("ENTER to return");
						scan.nextLine();
						break;
					}
					
					int urutan = -1;
					String newEmployeeId = "";
					String newEmployeeName = "";
					String newGender = "";
					String newPosition = "";
					
					do { 
						System.out.print("Input nomor urutan karyawan yang ingin diupdate: ");
						urutan = scan.nextInt()-1;
						scan.nextLine();
					} while(urutan < 0 || urutan > employee.size()-1);
					
					do {
						System.out.print("Buat kode karyawan baru [Y/N]: ");
						newEmployeeId = scan.nextLine();
					} while(!newEmployeeId.equals("Y") && !newEmployeeId.equals("N"));
					
					do {
						System.out.print("Input nama karyawan [>= 3]: ");
						newEmployeeName = scan.nextLine();
					} while(newEmployeeName.length() < 3 && !newEmployeeName.equals("0"));
					
					do {
						System.out.print("Input jenis kelamin [Laki-laki | Perempuan] (Case Sensitive): ");
						newGender = scan.nextLine();
					} while(!newGender.equals("Laki-laki") && !newGender.equals("Perempuan") && !newGender.equals("0"));
					
					do {
						System.out.print("Input jabatan [Manager | Supervisor | Admin] (Case Sensitive): ");
						newPosition = scan.nextLine();
					} while(!newPosition.equals("Manager") && !newPosition.equals("Supervisor") && !newPosition.equals("Admin") && !newPosition.equals("0"));
					
					if (newEmployeeId.equals("Y")) {
						employee.get(urutan).setEmployeeId(createEmployeeId());
					}
					if (!newEmployeeName.equals("0")) {
						employee.get(urutan).setName(newEmployeeName);
					}
					if (!newGender.equals("0")) {
						employee.get(urutan).setGender(newGender);
					}
					if (!newPosition.equals("0")) {
						String oldPosition = employee.get(urutan).getPosition();
						if (oldPosition.equals("Manager")) {
							manager--;
						} else if (oldPosition.equals("Supervisor")) {
							supervisor--;
						} else {
							admin--;
						}
						employee.get(urutan).setPosition(newPosition);
						employee.get(urutan).setSalary(calculateSalary(newPosition));
						tempEmployeeId.clear();
					}
					System.out.println("Berhasil mengupdate karyawan dengan id " + employee.get(urutan).getEmployeeId());
					System.out.println("ENTER to return");
					scan.nextLine();				
					break;
				}
				case 4: {
					cls();
					viewEmployee();
					if (employee.size() == 0) {
						System.out.println("Tidak ada karyawan yang bisa dihapus");
						System.out.println("ENTER to return");
						scan.nextLine();
						break;
					}
					
					int urutan = -1;
					
					do { 
						System.out.print("Input nomor urutan karyawan yang ingin dihapus [0 untuk cancel]: ");
						urutan = scan.nextInt();
						scan.nextLine();
					} while(urutan < 0 || urutan > employee.size());
					
					if (urutan == 0) {
						break;
					}
					
					String id = employee.get(urutan-1).getEmployeeId();
					String position = employee.get(urutan-1).getPosition();
					if (position.equals("Manager")) {
						manager--;
					} else if (position.equals("Supervisor")) {
						supervisor--;
					} else {
						admin--;
					}
					employee.remove(urutan-1);
					System.out.println("Karyawan dengan kode " + id + " berhasil dihapus");
					System.out.println("ENTER to return");
					scan.nextLine();
					break;
				}
			}
		}
	}

	public static void main(String[] args) {
		new Main();
	}

}
