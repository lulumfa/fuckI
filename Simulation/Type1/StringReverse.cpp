
// Question: Implement a function that reverse a C string
// Explanation: Quite easy, in place swap the end of the string with the start of the string
// Contributor: Edward Liu

void reverse(char* str)
{
	char* end = str;
	char tmp;
	if(str)
	{
		while(*end)
		{
			end++;
		}
		end--;

		while(str < end)
		{
			tmp = *str;
			*str++ = *end;
			*end-- = tmp;
		}
	}
}