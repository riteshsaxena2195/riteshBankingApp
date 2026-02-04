import axios from 'axios';
import * as debug from './apiDebug';

const API_BASE_URL = process.env.REACT_APP_API_URL;
const DEBUG = false;

const instance = axios.create({
  baseURL: API_BASE_URL,
  timeout: 2000,
});

export const customerBuildDto = (formData) => {
  const data = {
    customerId: formData.get('customerId'),
    customerType: formData.get('customerType'),
    name: formData.get('name'),
    streetNumber: formData.get('streetNumber'),
    city: formData.get('city'),
    province: formData.get('province'),
    postalCode: formData.get('postalCode'),
    accountIds: [],
  };

  if (formData.get('accountIds') !== null) {
    data.accountIds = formData.get('accountIds').split(',');
  }

  return data;
};

export const accountBuildDto = (formData) => {
  const data = {
    accountId: formData.get('accountId'),
    accountType: formData.get('accountType'),
    balance: parseFloat(formData.get('balance')),
    customerId: formData.get('customerId'),
  };

  if (formData.get('nextCheckNumber'))
    data.nextCheckNumber = parseInt(formData.get('nextCheckNumber'));

  if (formData.get('interestRate'))
    data.interestRate = parseFloat(formData.get('interestRate'));

  return data;
};

// ✅ Tek ve doğru tanım
export const getAccounts = async () => {
  if (DEBUG) return debug.getAccounts();

  try {
    const response = await instance.get(`/accounts`);
    return response.data;
  } catch (error) {
    throw error;
  }
};

export const getAccount = async (id) => {
  if (DEBUG) return debug.getAccount(id);

  try {
    const response = await instance.get(`/accounts/${id}`);
    return response.data;
  } catch (error) {
    throw error;
  }
};

export const createAccount = async (data) => {
  if (DEBUG) return debug.createAccount(data);

  try {
    const response = await instance.post(`/accounts`, data);
    return response.data;
  } catch (error) {
    throw error;
  }
};

export const updateAccount = async (id, data) => {
  if (DEBUG) return debug.updateAccount(id, data);

  try {
    const response = await instance.put(`/accounts/${id}`, data);
    return response.data;
  } catch (error) {
    throw error;
  }
};

export const deleteAccount = async (id) => {
  if (DEBUG) return debug.deleteAccount(id);

  try {
    await instance.delete(`/accounts/${id}`);
  } catch (error) {
    throw error;
  }
};

export const getCustomers = async () => {
  if (DEBUG) return debug.getCustomers();

  try {
    const response = await instance.get(`/customers`);
    return response.data;
  } catch (error) {
    throw error;
  }
};

export const getCustomer = async (id) => {
  if (DEBUG) return debug.getCustomer(id);

  try {
    const response = await instance.get(`/customers/${id}`);
    return response.data;
  } catch (error) {
    throw error;
  }
};

export const createCustomer = async (data) => {
  if (DEBUG) return debug.createCustomer(data);

  try {
    const response = await instance.post(`/customers`, data);
    return response.data;
  } catch (error) {
    throw error;
  }
};

export const updateCustomer = async (id, data) => {
  if (DEBUG) return debug.updateCustomer(id, data);

  try {
    const response = await instance.put(`/customers/${id}`, data);
    return response.data;
  } catch (error) {
    throw error;
  }
};

export const deleteCustomer = async (id) => {
  if (DEBUG) return debug.deleteCustomer(id);

  try {
    await instance.delete(`/customers/${id}`);
  } catch (error) {
    throw error;
  }
};

export const getCustomerAccounts = async (id) => {
  if (DEBUG) return debug.getCustomerAccounts(id);

  try {
    const response = await instance.get(`/management/getCustomerAccounts/${id}`);
    return response.data;
  } catch (error) {
    throw error;
  }
};
