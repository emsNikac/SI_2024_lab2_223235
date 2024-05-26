# Никола Христовски 223235
## Control flow graph
![Screenshot 2024-05-26 171143](https://github.com/emsNikac/SI_2024_lab2_223235/assets/165374773/fe38b3a6-e002-4d34-9405-6bb66fc5038b)</br>
- C = E – N + 2P </br>
•	E е бројот на рабови во графот</br>
•	N е бројот на јазли во графот</br>
•	P е број на поврзани компоненти</br>
- Рабови: 22</br>
o	Start</br>
o	Check if allItems is null</br>
o	Throw Exception (allItems null)</br>
o	Initialize sum</br>
o	For each item</br>
o	Check if name is null or empty</br>
o	Set name to "unknown"</br>
o	Check if barcode is null</br>
o	Throw Exception (no barcode)</br>
o	For each character in barcode</br>
o	Is character valid</br>
o	Throw Exception (invalid character)</br>
o	Has discount</br>
o	Add price * discount to sum</br>
o	Add price to sum</br>
o	Check specific discount condition</br>
o	Subtract 30 from sum</br>
o	End loop</br>
o	Is sum <= payment</br>
o	Return true</br>
o	Return false</br>
o	End</br>
- Број на јазли: 27</br>
o	Start -> Check if allItems is null</br>
o	Check if allItems is null -> Yes -> Throw Exception</br>
o	Check if allItems is null -> No -> Initialize sum</br>
o	Initialize sum -> For each item</br>
o	For each item -> Check if name is null or empty</br>
o	Check if name is null or empty -> Yes -> Set name to "unknown"</br>
o	Set name to "unknown" -> Check if barcode is null</br>
o	Check if name is null or empty -> No -> Check if barcode is null</br>
o	Check if barcode is null -> Yes -> Throw Exception</br>
o	Check if barcode is null -> No -> For each character in barcode</br>
o	For each character in barcode -> Is character valid</br>
o	Is character valid -> Yes -> Has discount</br>
o	Is character valid -> No -> Throw Exception</br>
o	Has discount -> Yes -> Add price * discount to sum</br>
o	Has discount -> No -> Add price to sum</br>
o	Add price * discount to sum -> Check specific discount condition</br>
o	Add price to sum -> Check specific discount condition</br>
o	Check specific discount condition -> Yes -> Subtract 30 from sum</br>
o	Check specific discount condition -> No -> End loop</br>
o	Subtract 30 from sum -> End loop</br>
o	End loop -> For each item (loop back)</br>
o	End loop -> Is sum <= payment</br>
o	Is sum <= payment -> Yes -> Return true</br>
o	Is sum <= payment -> No -> Return false</br>
- Број на поврзани компоненти:</br>
o	Бидејќи во нашиот случај имаме само една поврзана помпонента, еден тек, P има предност 1</br>
-	Според формулата кога ќе замениме добиваме</br>
o	C = 27 – 22 + 2 * 1 => C = 7, 7 изнесува циклопатската комплексност на добиениот код</br>
## Every Branch
![Screenshot 2024-05-26 174223](https://github.com/emsNikac/SI_2024_lab2_223235/assets/165374773/8daacf76-e0b3-466b-9a13-39765f93c969)</br>

## Тест случаи според критериумот (Every Branch)

### Тест случај 1: Листа на предмети е null
- **Влез**: `allItems = null`, `payment = 100`
- **Очекуван резултат**: `RuntimeException("allItems list can't be null!")`
- **Објаснување**: Функцијата треба да фрли исклучок ако листата на предмети е null. Ова го проверува условот `if (allItems == null)`.

### Тест случај 2: Празна листа на предмети
- **Влез**: `allItems = []`, `payment = 100`
- **Очекуван резултат**: `true`
- **Објаснување**: Со празна листа на предмети, сумата е 0, која е помала од секоја позитивна уплата. Ова го проверува условот `if (allItems == null)` да е false и ја обработува празната листа.

### Тест случај 3: Предмет со null или празно име
- **Влез**: `allItems = [new Item(null, "12345", 100, 0)]`, `payment = 100`
- **Очекуван резултат**: `true`
- **Објаснување**: Ако името на предметот е null или празно, треба да се постави на "unknown". Ова го проверува условот `if (item.getName() == null || item.getName().length() == 0)`.

### Тест случај 4: Предмет со невалиден баркод
- **Влез**: `allItems = [new Item("correct1", "12a45", 100, 0)]`, `payment = 100`
- **Очекуван резултат**: `RuntimeException("Invalid character in item barcode!")`
- **Објаснување**: Функцијата треба да фрли исклучок ако баркодот содржи невалиден карактер. Ова го проверува условот `if (allowed.indexOf(c) == -1)`.

### Тест случај 5: Предмет без баркод
- **Влез**: `allItems = [new Item("correct1", null, 100, 0)]`, `payment = 100`
- **Очекуван резултат**: `RuntimeException("No barcode!")`
- **Објаснување**: Функцијата треба да фрли исклучок ако баркодот е null. Ова го проверува условот `if (item.getBarcode() == null)`.

### Тест случај 6: Предмет со попуст
- **Влез**: `allItems = [new Item("correct1", "12345", 100, 0.2f)]`, `payment = 80`
- **Очекуван резултат**: `true`
- **Објаснување**: Цената на предметот со попуст треба правилно да се пресмета. Ова го проверува условот `if (item.getDiscount() > 0)`.

### Тест случај 7: Предмет со цена поголема од 300, попуст и баркод што почнува со '0'
- **Влез**: `allItems = [new Item("correct1", "012345", 350, 0.1f)]`, `payment = 32`
- **Очекуван резултат**: `true`
- **Објаснување**: Цената на предметот треба да вклучи попуст и специјално намалување од 30 единици. Ова ги проверува условите `if (item.getPrice() > 300 && item.getDiscount() > 0 && item.getBarcode().charAt(0) == '0')`.

### Тест случај 8: Повеќе предмети со мешани валидни и невалидни податоци
- **Влез**: `allItems = [new Item("correct1", "12345", 100, 0), new Item("correct2", "54321", 200, 0.1f), new Item("correct3", "67890", 400, 0.2f)]`, `payment = 200`
- **Очекуван резултат**: `false`
- **Објаснување**: Вкупната сума со попусти треба да се пресмета и да се спореди со уплатата. Ова ги проверува сите услови во циклусот за секој предмет.

### Тест случај 9: Сума помала од уплатата
- **Влез**: `allItems = [new Item("correct1", "12345", 50, 0)]`, `payment = 60`
- **Очекуван резултат**: `true`
- **Објаснување**: Вкупната сума на цените е помала од уплатата. Ова го проверува условот `if (sum <= payment)` кога сумата е помала од уплатата.

### Тест случај 10: Сума поголема од уплатата
- **Влез**: `allItems = [new Item("correct1", "12345", 100, 0), new Item("correct2", "67890", 200, 0)]`, `payment = 250`
- **Очекуван резултат**: `false`
- **Објаснување**: Вкупната сума на цените е поголема од уплатата. Ова го проверува условот `if (sum <= payment)` кога сумата е поголема од уплатата.

## Multiple condition (влезните параметри се напишани во објаснувањето)

![Screenshot 2024-05-26 172611](https://github.com/emsNikac/SI_2024_lab2_223235/assets/165374773/481c1018-28c9-41b6-aca3-b0f5a8a3462e)

## Тест случаи според критериумот (Multiple Condition)

### Тест случај 1: Сите услови се точни
- **Влез**: `allItems = [new Item("correct1", "012345", 350, 0.1f)]`, `payment = 35`
- **Очекуван резултат**: `true`
- **Објаснување**: Сите услови се точни.
  - `item.getPrice() > 300` е точно.
  - `item.getDiscount() > 0` е точно.
  - `item.getBarcode().charAt(0) == '0'` е точно.

### Тест случај 2: Третиот услов е неточен
- **Влез**: `allItems = [new Item("correct1", "112345", 350, 0.1f)]`, `payment = 35`
- **Очекуван резултат**: `false`
- **Објаснување**: Третиот услов е неточен.
  - `item.getPrice() > 300` е точно.
  - `item.getDiscount() > 0` е точно.
  - `item.getBarcode().charAt(0) == '0'` е неточно.

### Тест случај 3: Вториот услов е неточен
- **Влез**: `allItems = [new Item("correct1", "012345", 350, 0.0f)]`, `payment = 35`
- **Очекуван резултат**: `false`
- **Објаснување**: Вториот услов е неточен.
  - `item.getPrice() > 300` е точно.
  - `item.getDiscount() > 0` е неточно.
  - `item.getBarcode().charAt(0) == '0'` е точно.

### Тест случај 4: Вториот и третиот услов се неточни
- **Влез**: `allItems = [new Item("correct1", "112345", 350, 0.0f)]`, `payment = 35`
- **Очекуван резултат**: `false`
- **Објаснување**: Вториот и третиот услов се неточни.
  - `item.getPrice() > 300` е точно.
  - `item.getDiscount() > 0` е неточно.
  - `item.getBarcode().charAt(0) == '0'` е неточно.

### Тест случај 5: Првиот услов е неточен
- **Влез**: `allItems = [new Item("correct1", "012345", 250, 0.1f)]`, `payment = 25`
- **Очекуван резултат**: `false`
- **Објаснување**: Првиот услов е неточен.
  - `item.getPrice() > 300` е неточно.
  - `item.getDiscount() > 0` е точно.
  - `item.getBarcode().charAt(0) == '0'` е точно.

### Тест случај 6: Првиот и третиот услов се неточни
- **Влез**: `allItems = [new Item("correct1", "112345", 250, 0.1f)]`, `payment = 25`
- **Очекуван резултат**: `false`
- **Објаснување**: Првиот и третиот услов се неточни.
  - `item.getPrice() > 300` е неточно.
  - `item.getDiscount() > 0` е точно.
  - `item.getBarcode().charAt(0) == '0'` е неточно.

### Тест случај 7: Првиот и вториот услов се неточни
- **Влез**: `allItems = [new Item("correct1", "012345", 250, 0.0f)]`, `payment = 25`
- **Очекуван резултат**: `false`
- **Објаснување**: Првиот и вториот услов се неточни.
  - `item.getPrice() > 300` е неточно.
  - `item.getDiscount() > 0` е неточно.
  - `item.getBarcode().charAt(0) == '0'` е точно.

### Тест случај 8: Сите услови се неточни
- **Влез**: `allItems = [new Item("correct1", "112345", 250, 0.0f)]`, `payment = 25`
- **Очекуван резултат**: `false`
- **Објаснување**: Сите услови се неточни.
  - `item.getPrice() > 300` е неточно.
  - `item.getDiscount() > 0` е неточно.
  - `item.getBarcode().charAt(0) == '0'` е неточно.
