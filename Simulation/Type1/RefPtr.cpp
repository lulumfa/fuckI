
// Question: Implement a generic smart pointer class.

// Explanation: Smart pointer is one of the most used data structure in C++ (Not java though I think, where all memory
// operations are prohibited). The idea of its implementation is quite straightforward. We implement a template 
// class which contains the pointer, and a reference count denoting how many times that pointer has been
// referenced by possibly other smart pointers. Therefore, naturally, when it's being assigned, we should
// increment the reference counter. When the smart pointer goes out of scope (its destructor is called),
// we decrement the reference counter and free the raw pointer if and only if the reference counter reaches zero.
// It's simple logic. However to have a robust class that allows you manipulate it as if it's a raw pointer is a
// little tricky. Read the comment below for details.

// Contributor: Edward Liu


#define NULL 0

// We have two type of destructor here for freeing the raw pointers,
// one for normal pointers, the other for array pointers
class RefPtrDefaultDestructor
{
public:
	template<class T>
	static void Free(T* ptr)
	{
		delete ptr;
	}
};

class RefPtrArrayDestructor
{
public:
	template<class T>
	static void Free(T* ptr)
	{
		delete [] ptr;
	}
};

template<class T, class Destructor = RefPtrDefaultDestructor>
class RefPtr
{
private:
	template<class T1, class Destructor1>
	friend class RefPtr; // This allows other pointer type to access your data member

	T* pPointer;		// Raw pointer
	size_t* piRefCount; // Reference counter

public:
	RefPtr()
	{
		pPointer = NULL;
		piRefCount = NULL;
	}
	RefPtr(T* ptr)
		: pPointer(NULL), piRefCount(NULL)
	{
		this->operator=(ptr);
	}

	template<typename T1>
	RefPtr(T1* ptr)
		: pPointer(NULL), piRefCount(NULL) // This allows type conversion between different types of smart pointers
	{
		this->operator=(ptr);
	}

	RefPtr(const RefPtr<T, Destructor>& ptr)
		: pPointer(NULL), piRefCount(NULL)
	{
		this->operator=(ptr);
	}

	RefPtr(RefPtr<T, Destructor>&& ptr)
		: pPointer(NULL), piRefCount(NULL)
	{
		this->operator=(std::move(ptr));
	}

	RefPtr<T, Destructor>& operator = (T* ptr) // Basic assignment operator
	{
		Dereference(); // First deference the current pointer

		pPointer = ptr;
		if(ptr)
		{
			piRefCount = new size_t; // Alloc space for the counter and initialize it as 1
			(*piRefCount) = 1;
		}
		else
			piRefCount = NULL;

		return *this;
	}

	template<typename T1>
	RefPtr<T, Destructor>& operator = (T1* ptr) // Same as above, but this allows type casting
	{
		Dereference();

		pPointer = dynamic_cast<T*>(ptr); // Use dynamic cast here for safety reason, identify illegal cast at runtime
		if(ptr)
		{
			piRefCount = new size_t;
			(*piRefCount) = 1;
		}
		else
			piRefCount = NULL;

		return *this;
	}

	RefPtr<T, Destructor>& operator = (const RefPtr<T, Destructor>& ptr) // When assigned with another smart pointer
	{
		if(ptr.pPointer != pPointer) // Make sure they are now referencing the same raw pointer
		{
			Dereference(); // First deference and then copy all the value
			pPointer = ptr.pPointer;
			piRefCount = ptr.piRefCount;
			if (piRefCount)
				(*piRefCount)++;
		}
		return *this;
	}

	template<typename T1>
	RefPtr(const RefPtr<T1>& ptr)
		: pPointer(NULL), piRefCount(NULL)
	{
		this->operator=(ptr);
	}

	template<typename T1>
	RefPtr<T, Destructor>& operator = (const RefPtr<T1, Destructor>& ptr) // Same as above, now with type casting
	{
		if(ptr.pPointer != pPointer)
		{
			Dereference();
			pPointer = dynamic_cast<T*>(ptr.pPointer); // Again, use dynamic cast for safety
			piRefCount = ptr.iRefCount;
			(*piRefCount)++;
		}
		return *this;
	}

	bool operator == (const T* ptr) const // Other common operators below
	{
		return pPointer == ptr;
	}
	bool operator != (const T* ptr) const
	{
		return pPointer != ptr;
	}
	bool operator == (const RefPtr<T>& ptr) const
	{
		return pPointer == ptr.pPointer;
	}
	bool operator != (const RefPtr<T>& ptr) const
	{
		return pPointer != ptr.pPointer;
	}

	T* operator + (int offset) const
	{
		return pPointer + offset;
	}
	T& operator [] (int idx) const
	{
		return *(pPointer + idx);
	}

	RefPtr<T, Destructor>& operator = (RefPtr<T, Destructor>&& ptr) // Same as above, now with right value reference
	{
		if(ptr.pointer != pPointer)
		{
			Dereference();
			pPointer = ptr.pointer;
			piRefCount = ptr.refCount;
			ptr.pointer = NULL;
			ptr.refCount = NULL;
		}
		return *this;
	}

	T* Release() // This is called when you want to release acquisition for the current raw pointer
	{
		if(pPointer)
		{
			if((*piRefCount) > 1) // Decrement the reference counter, or free it if it's 1
			{
				(*piRefCount)--;
			}
			else
			{
				delete piRefCount;
			}
		}
		auto rs = pPointer; // Unset all members and return the raw pointer here
		piRefCount = NULL;
		pPointer = NULL;
		return rs;
	}

	~RefPtr()
	{
		Dereference(); // Dereference when dustructed
	}

	void Dereference()
	{
		if(pPointer)
		{
			if((*piRefCount) > 1) // If counter is greater than 1, decrement it
			{
				(*piRefCount)--;
			}
			else // Otherwise free the raw pointer
			{
				Destructor::Free(pPointer); // Using template here because could have different type of destructor
				delete piRefCount;
			}
		}
	}
	T& operator * () const
	{
		return *pPointer;
	}
	T* operator -> () const
	{
		return pPointer;
	}
	T* Ptr() const
	{
		return pPointer;
	}

	operator void * () const // () operator, allows you to use something like if(smartPtr) { ... }
	{
		if(pPointer)
			return (void*)(pPointer);
		else
			return NULL;
	}
};

// Example usage below
int main()
{
	{
		RefPtr<int> pA = new int(10); // pA's reference counter is 1
		RefPtr<int> pB = pA; // Both pA and pB's reference counter are 2 (The counters share memory)
		int* pRaw = pA.Release(); // pA is released and pB's counter is now 1
		RefPtr<int> pC;
		if(pA) // False, because pA has been released
			pC = pA;
		if(pB) // True
			pC = pA;
	}

	// Now pB is out of scope, the newed memory is automatically released
	RefPtr<int> pC = new float(5.0f); // Unsafe type cast, gives you compiling error

	class Base
	{

	};
	class Derive : public Base
	{

	};
	RefPtr<Base> pBase = new Derive; // Correct
	RefPtr<Derive> pDerive = new Base; // Illegal cast, gives you compiling error

	class Rand
	{

	};
	RefPtr<Base> pBase2 = new Rand; // Illegal cast, gives you compiling error
}