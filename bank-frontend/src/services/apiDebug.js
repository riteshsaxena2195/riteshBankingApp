var debugCustomers = [
    { customerId: '1001', customerType: 'Person', name: 'Aaron Bauch', streetNumber: '9770 Rose Centers', city: 'Austin', province: 'TX', postalCode: '78759', accountIds: ['1001', '1002'] },
    { customerId: '1002', customerType: 'Person', name: 'Mrs. Marhta Lebsack', streetNumber: '04830 Marion Crescent', city: 'Toronto', province: 'ON', postalCode: 'M5V 3L9', accountIds: ['1003', '1004'] },
    { customerId: '1003', customerType: 'Company', name: 'Kautzer, Larkin and Haley', streetNumber: '162 Fisher Port', city: 'Toronto', province: 'ON', postalCode: 'M5V 3L9', accountIds: ['1005', '1006'] },
    { customerId: '1004', customerType: 'Company', name: 'Orn, Anderson and Sanford', streetNumber: '95910 Ty Ford', city: 'Toronto', province: 'ON', postalCode: 'M5V 3L9', accountIds: ['1007', '1008'] },
    { customerId: '1005', customerType: 'Person', name: 'Valeri Collier', streetNumber: '74944 Schulist Cliff', city: 'Austin', province: 'TX', postalCode: '78759', accountIds: ['1009', '1010'] },
    { customerId: '1006', customerType: 'Company', name: 'Tillman LLC', streetNumber: '92877 Russel Road', city: 'Newark', province: 'NJ', postalCode: '07008', accountIds: ['1011'] },
    { customerId: '1007', customerType: 'Person', name: 'Rudy Zboncak III', streetNumber: '851 Aurelio Parkways', city: 'Austin', province: 'TX', postalCode: '78759', accountIds: ['1012', '1013'] },
    { customerId: '1008', customerType: 'Company', name: 'Herman-Nitzsche', streetNumber: '643 Dorthy Green', city: 'Newark', province: 'NJ', postalCode: '07008', accountIds: ['1014', '1015'] },
    { customerId: '1009', customerType: 'Person', name: 'Kattie Casper', streetNumber: '6481 Lemke Manors', city: 'Austin', province: 'TX', postalCode: '78759', accountIds: ['1016', '1017'] },
    { customerId: '1010', customerType: 'Person', name: 'Demetria Grimes', streetNumber: '12991 Edward Camp', city: 'Toronto', province: 'ON', postalCode: 'M5V 3L9', accountIds: ['1018'] },
];

var debugAccounts = [
    { accountId: '1001', accountType: 'Checking', balance: 54.26, nextCheckNumber: 1, customerId: '1001' },
    { accountId: '1002', accountType: 'Savings', balance: 25042.48, interestRate: 0.015, customerId: '1001' },
    { accountId: '1003', accountType: 'Checking', balance: 5108.04, nextCheckNumber: 43, customerId: '1002' },
    { accountId: '1004', accountType: 'Checking', balance: 481.19, nextCheckNumber: 5, customerId: '1002' },
    { accountId: '1005', accountType: 'Checking', balance: 897.54, nextCheckNumber: 6, customerId: '1003' },
    { accountId: '1006', accountType: 'Savings', balance: 19758.14, interestRate: 0.015, customerId: '1003' },
    { accountId: '1007', accountType: 'Savings', balance: 8050.03, interestRate: 0.021, customerId: '1004' },
    { accountId: '1008', accountType: 'Savings', balance: 52605.22, interestRate: 0.015, customerId: '1004' },
    { accountId: '1009', accountType: 'Checking', balance: 8.07, nextCheckNumber: 19, customerId: '1005' },
    { accountId: '1010', accountType: 'Savings', balance: 250.33, interestRate: 0.014, customerId: '1005' },
    { accountId: '1011', accountType: 'Checking', balance: 212.10, nextCheckNumber: 101, customerId: '1006' },
    { accountId: '1012', accountType: 'Checking', balance: 7057.12, nextCheckNumber: 97, customerId: '1007' },
    { accountId: '1013', accountType: 'Savings', balance: 32871.97, interestRate: 0.019, customerId: '1007' },
    { accountId: '1014', accountType: 'Checking', balance: 4.84, nextCheckNumber: 4, customerId: '1008' },
    { accountId: '1015', accountType: 'Checking', balance: 113.12, nextCheckNumber: 2, customerId: '1008' },
    { accountId: '1016', accountType: 'Checking', balance: 1283.84, nextCheckNumber: 3, customerId: '1009' },
    { accountId: '1017', accountType: 'Savings', balance: 17034.43, interestRate: 0.012, customerId: '1009' },
    { accountId: '1018', accountType: 'Checking', balance: 14346.01, nextCheckNumber: 5, customerId: '1010' },
];

export const getAccounts = () => {
  console.log(`API: getting accounts`);

  const response = debugAccounts;

  return response;
}

export const getAccount = (id) => {
  console.log(`API: getting account id=${id}`);

  const response = debugAccounts.filter(account => account.accountId === id)[0]

  return response;
}

export const createAccount = (data) => {
  console.log(`API: creating account data=${data}`);

  data.accountId = (parseInt(debugAccounts[debugAccounts.length - 1].accountId) + 1).toString();
  debugAccounts = debugAccounts.concat(data);

  const response = data;

  return response;
}

export const updateAccount = (id, data) => {
  console.log(`API: updating account id=${id}`);

  data.accountId = id;
  Object.values(debugAccounts).forEach(account => {
    if (account.accountId === id) {
        account.balance = data.balance;
        account.interestRate = data.interestRate;
        account.nextCheckNumber = data.nextCheckNumber;
    }
  })

  const response = data;

  return response;
}

export const deleteAccount = (id) => {
  console.log(`API: deleting account id=${id}`);

  debugAccounts = debugAccounts.filter(account => account.accountId !== id);

  return;
}

export const getCustomers = () => {
  console.log(`API: getting customers`);

  const response = debugCustomers;

  return response;
}

export const getCustomer = (id) => {
  console.log(`API: getting customer id=${id}`);

  const response = debugCustomers.filter(customer => customer.customerId === id)[0];

  return response;
}

export const createCustomer = (data) => {
  console.log(`API: creating customer data=${data}`);

  data.customerId = (parseInt(debugCustomers[debugCustomers.length - 1].customerId) + 1).toString();
  debugCustomers = debugCustomers.concat(data);

  const response = data;

  return response;
}

export const updateCustomer = (id, data) => {
  console.log(`API: updating customer id=${id} data=${data}`);

  data.customerId = id;
  Object.values(debugCustomers).forEach(customer => {
    if (customer.customerId === id) {
        customer.name = data.name;
        customer.streetNumber = data.streetNumber;
        customer.city = data.city;
        customer.province = data.province;
        customer.postalCode = data.postalCode;
    }
  })

  const response = data;

  return response;
}

export const deleteCustomer = (id) => {
  console.log(`API: deleting customer id=${id}`);

  debugCustomers = debugCustomers.filter(customer => customer.customerId !== id);
  debugAccounts = debugAccounts.filter(account => account.customerId !== id);

  return;
}

export const getCustomerAccounts = (id) => {
  console.log(`API: getting customer accounts id=${id}`);

  const response = debugAccounts.filter(account => account.customerId === id);

  return response;
}
